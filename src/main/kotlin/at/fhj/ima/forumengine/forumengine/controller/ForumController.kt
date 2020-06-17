package at.fhj.ima.forumengine.forumengine.controller

import at.fhj.ima.forumengine.forumengine.entity.Forum
import at.fhj.ima.forumengine.forumengine.repository.ForumRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Controller
class ForumController(
        val forumRepository: ForumRepository
) {

    @RequestMapping("/forums", method = [RequestMethod.GET])
    fun forums(model: Model): String {
        model["forums"] = forumRepository.findAll()
        return "forums"
    }
}