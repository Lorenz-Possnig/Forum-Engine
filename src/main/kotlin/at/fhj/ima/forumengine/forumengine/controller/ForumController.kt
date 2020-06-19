package at.fhj.ima.forumengine.forumengine.controller

import at.fhj.ima.forumengine.forumengine.entity.Answer
import at.fhj.ima.forumengine.forumengine.entity.Forum
import at.fhj.ima.forumengine.forumengine.entity.Question
import at.fhj.ima.forumengine.forumengine.repository.AnswerRepository
import at.fhj.ima.forumengine.forumengine.repository.ForumRepository
import at.fhj.ima.forumengine.forumengine.repository.QuestionRepository
import at.fhj.ima.forumengine.forumengine.repository.UserRepository
import at.fhj.ima.forumengine.forumengine.security.FeUserPrincipal
import com.sun.org.apache.xpath.internal.operations.Bool
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import java.security.Principal
import java.util.*
import javax.validation.Valid

@Controller
class ForumController(
        val forumRepository: ForumRepository,
        val questionRepository: QuestionRepository,
        val userRepository: UserRepository
) {

    @RequestMapping("/forums", method = [RequestMethod.GET])
    fun forums(model: Model,
               @RequestParam(required=false) search: String?): String {
        val forums: List<Forum> = forumRepository.findAll()
        model["forums"] = if (search == null) {
            forums
        } else {
            forums.filter { it.name.toLowerCase().contains(search.toLowerCase()) ||
                            it.description.toLowerCase().contains(search.toLowerCase()) }
        }
        return "forums"
    }

    @RequestMapping("/forum", method = [RequestMethod.GET])
    fun showForum(model: Model,
                  @RequestParam(required = true) id: Int,
                  @RequestParam(required = false) search: String?): String {
        val questions: List<Question> =  questionRepository.findByForumId(id)
        model["questions"] = if (search == null) {
            questions
        } else {
            questions.filter {  it.title.toLowerCase().contains(search.toLowerCase()) ||
                                it.text.toLowerCase().contains(search.toLowerCase())}
        }
        model["forum"] = forumRepository.findById(id).get()
        return "forum"
        }

    @RequestMapping("/newforum", method = [RequestMethod.GET])
    fun newOrEditForum(model: Model, @RequestParam(required = false) forumId: Int?): String {
        if (forumId != null) {
            val forum = forumRepository.findById(forumId)
            model["forum"] = forum.get()
        }
            return "newforum"
    }

    @RequestMapping("/createforum", method = [RequestMethod.POST])
    fun createForum(@ModelAttribute("forum") @Valid forum: Forum,
                    @RequestParam("forumId", required = false) forumId: Int?,
                    principal: Principal,
                    bindingResult: BindingResult, model: Model): String {
        if (bindingResult.hasErrors()) {
            return "forums"
        } else {
            try {
                forum.createdBy = userRepository.findByUsername(principal.name)
                val alreadyExists: Boolean = forumRepository.existsById(forumId?:0)
                if (alreadyExists) {
                    //We use !! here because if forumId is null we cannot reach this block
                    forumRepository.updateById(forumId!!, forum.name, forum.description)
                } else {
                    forumRepository.save(forum)
                }
            } catch (dive: DataIntegrityViolationException) {
                if (dive.message.orEmpty().contains("name")) {
                    bindingResult.rejectValue("name", "name.alreadyInUse", "Name already in use.")
                    return "newforum"
                } else {
                    throw dive
                }
            }
        }
        return "redirect:/forum?id=" + forum.forumId.toString()
    }

    @RequestMapping("/deleteforum", method = [RequestMethod.GET])
    fun deleteForum(@RequestParam("forumId", required = true) forumId: Int): String {
        forumRepository.deleteById(forumId)
        return "redirect:forums"
    }
}