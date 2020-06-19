package at.fhj.ima.forumengine.forumengine.security

import at.fhj.ima.forumengine.forumengine.entity.User
import at.fhj.ima.forumengine.forumengine.entity.UserRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class FeUserPrincipal(val user: User) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = HashSet<GrantedAuthority>()
        when (user.role) {
            UserRole.ROLE_ADMIN -> {
                authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
                authorities.add(SimpleGrantedAuthority("ROLE_MOD"))
                authorities.add(SimpleGrantedAuthority("ROLE_USER"))
            }
            UserRole.ROLE_MOD   -> {
                authorities.add(SimpleGrantedAuthority("ROLE_MOD"))
                authorities.add(SimpleGrantedAuthority("ROLE_USER"))
            }
            UserRole.ROLE_USER  -> authorities.add(SimpleGrantedAuthority("ROLE_USER"))
        }
        return authorities
    }

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = user.username

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = user.password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
}