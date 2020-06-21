package at.fhj.ima.forumengine.forumengine.controller

import at.fhj.ima.forumengine.forumengine.entity.PersistedImage
import at.fhj.ima.forumengine.forumengine.entity.User
import at.fhj.ima.forumengine.forumengine.repository.ForumRepository
import at.fhj.ima.forumengine.forumengine.repository.ImageRepository
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
        val questionRepository: QuestionRepository,
        val imageRepository: ImageRepository
) {
    @RequestMapping("", method = [RequestMethod.GET])
    fun homePage(): String = "redirect:forums"

    @RequestMapping("/register", method = [RequestMethod.GET])
    fun newUser(model: Model): String {
        model["user"] = User()
        return "register"
    }

    @RequestMapping("/ban", method = [RequestMethod.GET])
    fun banUser(model: Model,
                @RequestParam("userId", required = true) userId: Int): String {
        val user = userRepository.findById(userId).get()
        userRepository.banById(userId)
        return "redirect:user?userId=${user.userId}"
    }

    @RequestMapping("/unban", method = [RequestMethod.GET])
    fun unbanUser(model: Model,
                @RequestParam("userId", required = true) userId: Int): String {
        val user = userRepository.findById(userId).get()
        userRepository.unbanById(userId)
        return "redirect:user?userId=${user.userId}"
    }

    @RequestMapping("/resetUser", method = [RequestMethod.GET])
    fun resetUser(model: Model, @RequestParam("username", required = true) username: String, @ModelAttribute("plainSecurityQuestion") answer: String): String {
        val user = userRepository.findByUsername(username)
        return if (user.securityQuestionAnswer == answer) {
            model["user"] = user
            "register"
        } else {
            "error-403"
        }

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
                val doesExist = userRepository.existsById(user.userId?:0)
                if (doesExist) {
                    userRepository.updateById(
                            userId = user.userId!!,
                            firstname = user.firstname?:"",
                            lastname = user.lastname?:"",
                            password = user.password!!,
                            securityQuestion = user.securityQuestion?:"",
                            securityQuestionAnswer = user.securityQuestionAnswer!!)
                } else {
                    userRepository.save(user)
                }
            } catch (dive: DataIntegrityViolationException) {
                if (dive.message.orEmpty().contains("name")) {
                    bindingResult.rejectValue("username", "username.alreadyInUse", "Username already in use")
                    model["errorMessage"] = "Username already in use!"
                    return newUser(model)
                } else if (dive.message.orEmpty().contains("mail")) {
                    bindingResult.rejectValue("mail", "mail.alreadyInUse", "Email address already in use")
                    model["errorMessage"] = "Email address already in use!"
                } else {
                    throw dive
                }
            }
        }
        return "redirect:/forums"
    }

    @RequestMapping("/editUser", method = [RequestMethod.GET])
    fun createOrEditUser(model: Model,
                         @RequestParam("userId", required = false) userId: Int?): String {
        if (userId != null) {
            val user = userRepository.findById(userId).get()
            model["user"] = user
        }
        return "register"
    }

    @RequestMapping("/user", method = [RequestMethod.GET])
    fun showUser(model: Model,
                 @RequestParam("userId", required = true) userId: Int):String {
        val user = userRepository.findById(userId).get()
        val forums = forumRepository.findByUserId(user)
        val questions = questionRepository.findByUserId(user)
        val image: PersistedImage? = imageRepository.findByUser(user)
        model["user"] = user
        model["forums"] = forums
        model["questions"] = questions
        if (image != null) {
            model["image"] = image
        }
        return "showuser"
    }
}