package com.perseacado.boot.feedbackui.filter

import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Marco Eigletsberger, 24.06.16.
 */
@Component
open class FeedbackInjectorFilter: OncePerRequestFilter() {
    override fun doFilterInternal(req: HttpServletRequest?, res: HttpServletResponse?, filterChain: FilterChain?) {
        throw UnsupportedOperationException()

        filterChain?.doFilter(req, res)
    }
}