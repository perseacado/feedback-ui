package com.github.perseacado.feedbackui.slack.client

/**
 * @author Marco Eigletsberger, 24.06.16.
 */
interface SlackClient {
    fun postMessage(username: String, text: String, channel: String, url: String);
    fun uploadFile(filename: String, channel: String, file: ByteArray): String;
}