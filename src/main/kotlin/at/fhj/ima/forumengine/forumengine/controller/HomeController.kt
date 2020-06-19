package at.fhj.ima.forumengine.forumengine.controller

import at.fhj.ima.forumengine.forumengine.entity.User
import at.fhj.ima.forumengine.forumengine.repository.UserRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid

@Controller
class HomeController (
        val userRepository: UserRepository
) {
    @RequestMapping("", method = [RequestMethod.GET])
    fun homePage(): String = "redirect:forums"

    @RequestMapping("/register", method = [RequestMethod.GET])
    fun newUser(): String = "register"

    @RequestMapping("/createuser", method = [RequestMethod.POST])
    fun createUser(@ModelAttribute("user") @Valid user: User,
                   bindingResult: BindingResult):String {
        if (bindingResult.hasErrors()) {
            return "forums"
        } else {
            try {
                user.password = BCryptPasswordEncoder().encode(user.password)
                userRepository.save(user)
            } catch (dive: DataIntegrityViolationException) {
                if (dive.message.orEmpty().contains("name")) {
                    bindingResult.rejectValue("username", "username.alreadyInUse", "Username already in use")
                    return "forums"
                } else {
                    throw dive
                }
            }
        }
        return "redirect:/forums"
    }
}