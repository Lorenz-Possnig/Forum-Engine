package at.fhj.ima.forumengine.forumengine

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

fun main() {
    print(BCryptPasswordEncoder().encode("user"))
}