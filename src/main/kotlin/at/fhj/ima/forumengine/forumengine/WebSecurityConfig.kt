package at.fhj.ima.forumengine.forumengine


import at.fhj.ima.forumengine.forumengine.security.FeUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var feUserDetailsService: FeUserDetailsService

    @Override
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/register").anonymous()
            .antMatchers(HttpMethod.POST,"/createuser").anonymous()
            .antMatchers(HttpMethod.GET,"/error").permitAll()
            .antMatchers(HttpMethod.GET,"/error-404").permitAll()
            .antMatchers(HttpMethod.GET,"/error-403").permitAll()
            .antMatchers(HttpMethod.GET,"/error-500").permitAll()
            .antMatchers("/css/custom.css").permitAll()
            .antMatchers("/showuser").permitAll()
            .antMatchers("/user").permitAll()
            .antMatchers("/forums").permitAll()
            .antMatchers("/forum").permitAll()
            .antMatchers("/question").permitAll()
            .antMatchers("/newquestion").hasRole("USER")
            .antMatchers(HttpMethod.POST,"/createforum").hasRole("USER")
            .antMatchers(HttpMethod.POST, "/createquestion").hasRole("USER")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .defaultSuccessUrl("/forums", true)
            .and()
            .rememberMe().key("uniqueAndSecret")
            .userDetailsService(feUserDetailsService)
    }
}