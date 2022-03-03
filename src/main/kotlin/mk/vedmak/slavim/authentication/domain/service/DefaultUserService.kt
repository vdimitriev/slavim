package mk.vedmak.slavim.authentication.domain.service

import mk.vedmak.slavim.authentication.domain.model.User
import mk.vedmak.slavim.authentication.domain.repository.RoleRepository
import mk.vedmak.slavim.authentication.domain.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DefaultUserService(

    val userRepository: UserRepository,

    val roleRepository: RoleRepository,

    val bCryptPasswordEncoder: BCryptPasswordEncoder

    ) : UserService {

    @Transactional
    override fun createUser(user: User): User {
        user.password = bCryptPasswordEncoder.encode(user.password)
        roleRepository.findByName("ROLE_USER")?.let {
            user.addRoles(mutableListOf(it))
        }
        return userRepository.save(user)

    }
}