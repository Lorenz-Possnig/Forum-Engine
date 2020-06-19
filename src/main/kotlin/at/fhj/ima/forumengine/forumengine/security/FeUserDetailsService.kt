package at.fhj.ima.forumengine.forumengine.security

import at.fhj.ima.forumengine.forumengine.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class FeUserDetailsService(val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
            FeUserPrincipal(userRepository.findByUsername(username))
}