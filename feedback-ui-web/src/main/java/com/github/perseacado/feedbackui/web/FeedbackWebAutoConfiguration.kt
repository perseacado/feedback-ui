package com.github.perseacado.feedbackui.web

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * @author Marco Eigletsberger, 24.06.16.
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(name = arrayOf("feedback.disabled"), havingValue = "false", matchIfMissing = true)
@EnableConfigurationProperties(FeedbackWebConfigurationProperties::class)
@ComponentScan(basePackageClasses = arrayOf(FeedbackWebAutoConfiguration::class))
open class FeedbackWebAutoConfiguration {
}