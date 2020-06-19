package at.fhj.ima.forumengine.forumengine.controller

import at.fhj.ima.forumengine.forumengine.entity.Answer
import at.fhj.ima.forumengine.forumengine.entity.Forum
import at.fhj.ima.forumengine.forumengine.entity.Question
import at.fhj.ima.forumengine.forumengine.repository.AnswerRepository
import at.fhj.ima.forumengine.forumengine.repository.ForumRepository
import at.fhj.ima.forumengine.forumengine.repository.QuestionRepository
import at.fhj.ima.forumengine.forumengine.repository.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import java.security.Principal
import javax.validation.Valid

@Controller
class AnswerController(
        val forumRepository: ForumRepository,
        val questionRepository: QuestionRepository,
        val answerRepository: AnswerRepository,
        val userRepository: UserRepository
) {
    @RequestMapping("/createanswer", method = [RequestMethod.POST])
    fun createAnswer(@ModelAttribute("answer") @Valid answer: Answer,
                     @RequestParam(required = true) forumId: Int,
                     @RequestParam(required = true) questId: Int,
                     bindingResult: BindingResult,
                     principal: Principal,
                     model: Model): String {
        if (bindingResult.hasErrors()) {
            return "forums"
        } else {
            val forum: Forum
            val question: Question
            val forumOpt = forumRepository.findById(forumId)
            forum = forumOpt.get()
            val questionOpt = questionRepository.findById(questId)
            question = questionOpt.get()
            answer.question = question
            answer.createdBy = userRepository.findByUsername(principal.name)
            answerRepository.save(answer)
            return String.format("redirect:/question?forumId=%d&questId=%s", forum.forumId, question.questId)

        }
    }

    @RequestMapping("/editanswer", method = [RequestMethod.POST])
    fun editAnswer(@ModelAttribute("answer") @Valid answer: Answer,
                   @RequestParam(required = true) ansId: Int,
                   bindingResult: BindingResult,
                   model: Model): String {
        answerRepository.updateById(ansId, answer.text)
        return String.format("redirect:/question?forumId=%d&questId=%s", answer.question?.forum?.forumId, answer.question?.questId)
    }

    @RequestMapping("/deleteanswer", method = [RequestMethod.GET])
    fun deleteAnswer(@RequestParam("forumId", required = false) forumId: Int?,
                     @RequestParam("questId", required = false) questId: Int?,
                     @RequestParam("answId", required = true) answId: Int): String {
        answerRepository.deleteById(answId)
        return if (forumId != null) {
            if (questId != null) {
                "redirect:/question?forumId=${forumId}&questId=${questId}"
            } else {
                "redirect:/forum?forumId=${forumId}"
            }
        } else {
            "redirect:/forums"
        }
    }
}