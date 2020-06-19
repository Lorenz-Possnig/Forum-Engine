package at.fhj.ima.forumengine.forumengine.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.nio.file.Files
import java.nio.file.Paths
import javax.servlet.http.HttpServletRequest

@Controller
class ImageController {
    @RequestMapping("/getImage/{imageId}")
    @ResponseBody
    fun getImage(@PathVariable imageId: Int, request: HttpServletRequest): ByteArray {
        var rpath = request.getRealPath("/")
        rpath = "$rpath/$imageId"
        val path = Paths.get(rpath)
        return Files.readAllBytes(path)
    }
}