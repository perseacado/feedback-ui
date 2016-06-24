package com.perseacado.boot.feedbackui.web

import com.perseacado.feedbackui.core.FeedbackMessage
import com.perseacado.feedbackui.core.FeedbackService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * @author Marco Eigletsberger, 24.06.16.
 */
@RestController
@RequestMapping("**/__feedback/message")
open class FeedbackEndpointController @Autowired constructor(
    val feedbackService: FeedbackService) {

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun postFeedback(@Valid @RequestBody feedbackDto: FeedbackMessage) =
        feedbackService.processFeedback(feedbackDto)

}