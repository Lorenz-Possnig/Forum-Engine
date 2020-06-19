package at.fhj.ima.forumengine.forumengine.controller

import at.fhj.ima.forumengine.forumengine.entity.Forum
import at.fhj.ima.forumengine.forumengine.entity.Question
import at.fhj.ima.forumengine.forumengine.repository.AnswerRepository
import at.fhj.ima.forumengine.forumengine.repository.ForumRepository
import at.fhj.ima.forumengine.forumengine.repository.QuestionRepository
import at.fhj.ima.forumengine.forumengine.repository.UserRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*
import javax.validation.Valid

@Controller
class QuestionController(
        val forumRepository: ForumRepository,
        val questionRepository: QuestionRepository,
        val answerRepository: AnswerRepository,
        val userRepository: UserRepository
) {
    @RequestMapping("/question", method = [RequestMethod.GET])
    fun showQuestion(model: Model,
                     @RequestParam(required = true) forumId: Int,
                     @RequestParam(required = true) questId: Int,
                     @RequestParam(required = false) search: String?): String {
        val forum: Optional<Forum> = forumRepository.findById(forumId)
        val question: Optional<Question> = questionRepository.findById(questId)
        model["forum"] = forum.get()
        model["question"] = question.get()
        val answers = answerRepository.findByQuestion(questId)
        model["answers"] = if (search == null) {
            answers
        } else {
            answers.filter { it.text.toLowerCase().contains(search.toLowerCase()) }
        }
        return "question"
    }

    @RequestMapping("/newquestion", method=[RequestMethod.GET])
    fun newOrEditQuestion(model: Model,
                          @RequestParam(required = true) forumId: Int,
                          @RequestParam(required = false) questId: Int?): String {
        val forum = forumRepository.findById(forumId)
        model["forum"] = forum.get()
        if (questId != null) {
            model["question"] = questionRepository.findById(questId).get()
        }
        return "newquestion"
    }

    @RequestMapping("/createquestion", method = [RequestMethod.POST])
    fun createQuestion(@ModelAttribute("question") @Valid question: Question,
                       @RequestParam(required = true) forumId: Int,
                       @RequestParam(required = false) questId: Int?,
                       principal: Principal,
                       bindingResult: BindingResult,
                       model: Model): String {
        if (bindingResult.hasErrors()) {
            return "forums"
        } else {
            val forumopt = forumRepository.findById(forumId)
            var forum: Forum
            try {
                forum = forumopt.get()
                question.forum = forum
                question.createdBy = userRepository.findByUsername(principal.name)
                val alreadyExists: Boolean = questionRepository.existsById(questId?:0)
                if (alreadyExists) {
                    //We use !! here because if forumId == null we cannot reach this block
                    questionRepository.updateById(questId!!, question.title, question.text)
                } else {
                    questionRepository.save(question)
                }
            } catch (dive: DataIntegrityViolationException) {
                return "newforum"
            }
            forum = forumopt.get()
            return String.format("redirect:/question?forumId=%d&questId=%s", forum.forumId, question.questId)
        }
    }

    @RequestMapping("/deletequestion", method = [RequestMethod.GET])
    fun deleteQuestion(@RequestParam("forumId", required = false) forumId: Int?,
                       @RequestParam("questId", required = true) questId: Int): String {
        questionRepository.deleteById(questId)
        return if (forumId != null) {
            "redirect:/forum?forumId=${forumId}"
        } else {
            "redirect:/forums"
        }
    }
}