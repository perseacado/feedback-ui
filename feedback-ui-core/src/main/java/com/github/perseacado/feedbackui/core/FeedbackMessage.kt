package com.github.perseacado.feedbackui.core

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Marco Eigletsberger, 24.06.16.
 */
data class FeedbackMessage @JsonCreator constructor(
    @JsonProperty("from") val from: String,
    @JsonProperty("url") val url: String,
    @JsonProperty("userAgent") val userAgent: String,
    @JsonProperty("message") val message: String,
    @JsonProperty("screenshot") val screenshot: String) {
}