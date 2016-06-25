package com.github.perseacado.feedbackui.core

/**
 * @author Marco Eigletsberger, 24.06.16.
 */
interface FeedbackService {
    fun processFeedback(feedbackMessage: FeedbackMessage)
}