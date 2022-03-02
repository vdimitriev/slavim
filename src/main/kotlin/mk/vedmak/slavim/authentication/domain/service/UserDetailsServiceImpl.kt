package mk.vedmak.slavim.authentication.domain.service

import mk.vedmak.slavim.authentication.domain.model.User
import mk.vedmak.slavim.authentication.domain.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UserDetailsServiceImpl(val userRepository: UserRepository): UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepository.findById(username).get()
        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            mutableSetOf<SimpleGrantedAuthority>()
        )
    }
}