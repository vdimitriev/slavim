package mk.vedmak.slavim.authentication.domain.repository

import mk.vedmak.slavim.authentication.domain.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository: JpaRepository<Role, Int> {

    fun findByName(name: String): Role?

}