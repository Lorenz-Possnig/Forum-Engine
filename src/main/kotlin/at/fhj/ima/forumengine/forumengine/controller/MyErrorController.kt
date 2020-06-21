package at.fhj.ima.forumengine.forumengine.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest

@Controller
class MyErrorController : ErrorController {

    @RequestMapping("/error")
    fun errorPage(request: HttpServletRequest): String {
        return when (request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)) {
            HttpStatus.NOT_FOUND.value() -> "error-404"
            HttpStatus.FORBIDDEN.value() -> "error-403"
            HttpStatus.INTERNAL_SERVER_ERROR.value() -> "error-500"
            else -> "error"
        }
    }

    @Override
    override fun getErrorPath(): String {
        return "/error"
    }
}