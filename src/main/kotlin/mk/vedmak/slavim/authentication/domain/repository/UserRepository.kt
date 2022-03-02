package mk.vedmak.slavim.authentication.domain.repository

import mk.vedmak.slavim.authentication.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String>