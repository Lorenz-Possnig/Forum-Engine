package at.fhj.ima.forumengine.forumengine.controller

import at.fhj.ima.forumengine.forumengine.entity.PersistedImage
import at.fhj.ima.forumengine.forumengine.repository.ImageRepository
import at.fhj.ima.forumengine.forumengine.repository.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.security.Principal
import javax.servlet.http.HttpServletRequest



@Controller
class ImageController(
        val imageRepository: ImageRepository,
        val userRepository: UserRepository
) {

    @RequestMapping("/fileupload", method = [RequestMethod.POST])
    fun postImage(
            @RequestPart("image") multipartFile: MultipartFile,
            principal: Principal,
            model: Model):String {
            fun MultipartFile.toPersistedImage() = this.contentType?.let {
                PersistedImage(
                    bytes = this.bytes,
                    mime = it,
                    user = userRepository.findByUsername(principal.name)
                ) }
        val user = userRepository.findByUsername(principal.name)
        if (user.profilePicture != null) {
            imageRepository.updateByUser(multipartFile.bytes, user)
        } else {
            imageRepository.save(multipartFile.toPersistedImage()!!)
        }
        return "forums"
    }
}