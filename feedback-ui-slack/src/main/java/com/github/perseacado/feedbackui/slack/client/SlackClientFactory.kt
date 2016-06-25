package com.github.perseacado.feedbackui.slack.client

/**
 * @author Marco Eigletsberger, 24.06.16.
 */
object SlackClientFactory {

    /**
     * Creates a new [SlackClient] with the given [token].
     * @param[token] the Slack API token
     * @return a new [SlackClient] using the given [SlackClient].
     */
    fun create(token: String): SlackClient = SlackClientImpl(token)

}