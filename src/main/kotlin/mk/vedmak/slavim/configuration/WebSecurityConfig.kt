package mk.vedmak.slavim.configuration

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurityConfig(

    @Qualifier("userDetailsServiceImpl")
    val userDetailsService: UserDetailsService):

    WebSecurityConfigurerAdapter() {

    private val logger = KotlinLogging.logger {}

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/")
                .defaultSuccessUrl("/chat")
                .and()
            .logout()
                .logoutSuccessUrl("/")
                .and()
            .authorizeRequests()
                .antMatchers("/login", "/new-account", "/").permitAll()
                .antMatchers("/css/**", "/js/**", "/static/**").permitAll()
                //.antMatchers(HttpMethod.POST, "/chatroom").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/chatroom").hasRole("ADMIN")
                .anyRequest().authenticated()
    }

}