package com.github.perseacado.feedbackui.slack

import com.github.perseacado.feedbackui.core.FeedbackMessage
import com.github.perseacado.feedbackui.core.FeedbackService
import com.github.perseacado.feedbackui.slack.client.SlackClientFactory
import org.apache.commons.codec.binary.Base64
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Marco Eigletsberger, 24.06.16.
 */
@Service
internal open class SlackFeedbackService @Autowired constructor(
    val slackProperties: SlackConfigurationProperties
) : FeedbackService {

    private val slackClient = SlackClientFactory.create(slackProperties.token!!)

    override fun processFeedback(feedbackMessage: FeedbackMessage) {
        val filename = "feedback_" + System.currentTimeMillis()
        val imageData = Base64.decodeBase64(feedbackMessage.screenshot)
        val slackUsername = slackProperties.username!!
        val slackChannel = slackProperties.channel!!
        val imageUrl = slackClient.uploadFile(filename, slackChannel!!, imageData)
        val attachment = createAttachment(feedbackMessage, imageUrl)
        slackClient.postMessage(slackUsername, attachment, slackChannel, imageUrl)
    }

    private fun createAttachment(feedbackDto: FeedbackMessage, imageUrl: String): String {
        return "[{\"pretext\": \"New feedback has arrived!\", \"fields\": [" +
            "{\"title\": \"Who?\", \"value\": \"" + feedbackDto.from + "\" }," +
            "{\"title\": \"Where?\", \"value\": \"" + feedbackDto.url + "\" }," +
            "{\"title\": \"Browser?\", \"value\": \"" + feedbackDto.userAgent + "\" }," +
            "{\"title\": \"What?\", \"value\": \"" + feedbackDto.message + "\" }" +
            "], \"image_url\": \"" + imageUrl + "\" }]"
    }
}