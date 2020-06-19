package at.fhj.ima.forumengine.forumengine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
class ForumengineApplication : SpringBootServletInitializer() {
	@Bean
	fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

	@Bean
	fun resourceUrlEncodingFilterRegistration(): FilterRegistrationBean<ResourceUrlEncodingFilter> {
		val registration = FilterRegistrationBean<ResourceUrlEncodingFilter>()
		registration.setFilter(ResourceUrlEncodingFilter())
		registration.addUrlPatterns("/*")
		registration.setName("resourceUrlEncodingFilter")
		return registration
	}
}
fun main(args: Array<String>) {
	runApplication<ForumengineApplication>(*args)
}
