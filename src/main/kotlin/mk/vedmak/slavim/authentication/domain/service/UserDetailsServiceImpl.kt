package mk.vedmak.slavim.authentication.domain.service

import mk.vedmak.slavim.authentication.domain.model.User
import mk.vedmak.slavim.authentication.domain.repository.UserRepository
import mu.KotlinLogging
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(val userRepository: UserRepository): UserDetailsService {

    private val logger = KotlinLogging.logger {}

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        logger.info("Load user by username $username")
        val user: User = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("No user was found for username $username")
        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            mutableSetOf<SimpleGrantedAuthority>()
        )
    }
}