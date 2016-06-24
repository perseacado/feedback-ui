package com.perseacado.boot.feedbackui.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.support.WebContentGenerator
import java.io.OutputStream
import java.util.concurrent.TimeUnit
import javax.servlet.http.HttpServletResponse

/**
 * @author Marco Eigletsberger, 24.06.16.
 */
@Controller
@RequestMapping("**/__feedback/resources")
open class FeedbackResourcesController : WebContentGenerator {

    constructor() {
        cacheSeconds = TimeUnit.DAYS.toSeconds(30).toInt()
    }

    @RequestMapping(value = "/fb")
    fun getFeedbackJs(response: HttpServletResponse, os: OutputStream) {
        response.contentType = "text/javascript"
        FeedbackResourcesController::class.java.getResourceAsStream("/fb.js").let {
            it.copyTo(os)
        }
    }
}