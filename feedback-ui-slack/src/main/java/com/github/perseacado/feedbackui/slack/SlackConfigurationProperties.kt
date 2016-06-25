package com.github.perseacado.feedbackui.slack

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * @author Marco Eigletsberger, 24.06.16.
 */
@ConfigurationProperties(prefix = "feedback-ui.slack")
class SlackConfigurationProperties {
    var token: String? = null
    var username: String? = null
    var channel: String? = null
}