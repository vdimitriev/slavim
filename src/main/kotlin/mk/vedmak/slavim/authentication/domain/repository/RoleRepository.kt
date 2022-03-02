package mk.vedmak.slavim.authentication.domain.repository

import mk.vedmak.slavim.authentication.domain.model.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, Int> {
    fun findByName(name: String): Role?
}