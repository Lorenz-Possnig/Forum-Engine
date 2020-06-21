package at.fhj.ima.forumengine.forumengine.controller

import at.fhj.ima.forumengine.forumengine.entity.User
import at.fhj.ima.forumengine.forumengine.repository.ForumRepository
import at.fhj.ima.forumengine.forumengine.repository.QuestionRepository
import at.fhj.ima.forumengine.forumengine.repository.UserRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid

@Controller
class HomeController (
        val userRepository: UserRepository,
        val forumRepository: ForumRepository,
        val questionRepository: QuestionRepository
) {
    @RequestMapping("", method = [RequestMethod.GET])
    fun homePage(): String = "redirect:forums"

    @RequestMapping("/register", method = [RequestMethod.GET])
    fun newUser(model: Model): String {
        model["user"] = User()
        return "register"
    }

    @RequestMapping("/createuser", method = [RequestMethod.POST])
    fun createUser(@ModelAttribute("user") @Valid user: User,
                   bindingResult: BindingResult,
                   model: Model):String {
        if (bindingResult.hasErrors()) {
            return "register"
        } else {
            try {
                user.password = BCryptPasswordEncoder().encode(user.password)
                userRepository.save(user)
            } catch (dive: DataIntegrityViolationException) {
                if (dive.message.orEmpty().contains("name")) {
                    bindingResult.rejectValue("username", "username.alreadyInUse", "Username already in use")
                    model["errorMessage"] = "Username already in use!"
                    return newUser(model)
                } else {
                    throw dive
                }
            }
        }
        return "redirect:/forums"
    }

    @RequestMapping("/user", method = [RequestMethod.GET])
    fun showUser(model: Model,
                 @RequestParam("userId", required = true) userId: Int):String {
        val user = userRepository.findById(userId).get()
        val forums = forumRepository.findByUserId(user)
        val questions = questionRepository.findByUserId(user)
        model["user"] = user
        model["forums"] = forums
        model["questions"] = questions
        return "showuser"
    }
}