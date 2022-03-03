package mk.vedmak.slavim.authentication.domain.service

import mk.vedmak.slavim.authentication.domain.model.User
import mk.vedmak.slavim.authentication.domain.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(val userRepository: UserRepository): UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("No user was found for username $username")
        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            mutableSetOf<SimpleGrantedAuthority>()
        )
    }
}