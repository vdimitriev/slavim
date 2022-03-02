package mk.vedmak.slavim.authentication.domain.model

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
data class User(

    @Id
    @NotEmpty
    @Size(min = 5, max = 15)
    var username: String? = null,

    @NotEmpty
    @Size(min = 5)
    var password: String = "",

    @NotEmpty
    var name: String = "",

    @Email
    @NotEmpty
    var email: String = ""

) {

    fun addRoles(roles: Collection<Role>) {
    }
}