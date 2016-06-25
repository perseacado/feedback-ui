package com.github.perseacado.feedbackui.slack.client

import org.apache.http.HttpEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.ContentType
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.impl.client.HttpClients
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.nio.charset.Charset
import java.util.regex.Pattern

/**
 * @author Marco Eigletsberger, 24.06.16.
 */
internal class SlackClientImpl(val token: String) : SlackClient {

    private val API_BASE_URI = "https://slack.com"
    private val API_FILE_UPLOAD = API_BASE_URI + "/api/files.upload"
    private val API_POST_MESSAGE = API_BASE_URI + "/api/chat.postMessage"
    private val CHARSET = Charset.forName("UTF-8")
    private val PARAM_TOKEN = "token"
    private val PARAM_USERNAME = "username"
    private val PARAM_CHANNEL = "channel"
    private val PARAM_CHANNELS = "channels"
    private val PARAM_FILENAME = "filename"
    private val PARAM_FILE = "file"
    private val PARAM_ATTACHMENTS = "attachments"

    private val httpclient = HttpClients.createMinimal()

    override fun postMessage(username: String, text: String, channel: String, url: String) {
        val entity = MultipartEntityBuilder
            .create()
            .setCharset(CHARSET)
            .addTextBody(PARAM_TOKEN, token)
            .addTextBody(PARAM_USERNAME, username)
            .addTextBody(PARAM_CHANNEL, channel)
            .addTextBody(PARAM_ATTACHMENTS, text, ContentType.APPLICATION_JSON)
            .build()
        execute(API_POST_MESSAGE, entity) { it }
    }

    override fun uploadFile(filename: String, channel: String, file: ByteArray): String {
        val entity = MultipartEntityBuilder
            .create()
            .setCharset(CHARSET)
            .addBinaryBody(PARAM_FILE, file, ContentType.DEFAULT_BINARY, filename)
            .addTextBody(PARAM_TOKEN, token)
            .addTextBody(PARAM_CHANNELS, channel)
            .addTextBody(PARAM_FILENAME, filename)
            .build()
        return execute(API_FILE_UPLOAD, entity) {
            val matcher = Pattern.compile("\"permalink\":\"([^\"]*)\"").matcher(it)
            matcher.find()
            matcher.group(1).replace("\\\\".toRegex(), "")
        }
    }

    @Throws(IOException::class)
    private fun <T> execute(uri: String, httpEntity: HttpEntity, transformer: (String) -> T): T {
        val httpPost = HttpPost(uri)
        httpPost.entity = httpEntity

        httpclient.execute(httpPost).use { response ->
            val outputStream = ByteArrayOutputStream()
            response.entity.writeTo(outputStream)
            return transformer(outputStream.toString())
        }
    }
}