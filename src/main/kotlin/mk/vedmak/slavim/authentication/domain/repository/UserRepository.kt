package mk.vedmak.slavim.authentication.domain.repository

import mk.vedmak.slavim.authentication.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, String> {

    fun findByUsername(username: String): User?

}