package com.github.perseacado.feedbackui.web

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * @author Marco Eigletsberger, 24.06.16.
 */
@ConfigurationProperties(prefix = "feedback-ui")
class FeedbackWebConfigurationProperties {
    var disabled: Boolean = false
}