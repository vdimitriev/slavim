package mk.vedmak.slavim.authentication.domain.model

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id
    @SequenceGenerator(name = "users_id_sequence", sequenceName = "users_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,

    var username: String = "",
    var password: String = "",
    var name: String = "",
    var email: String = ""

    ) {

    fun addRoles(roles: Collection<Role>) {
    }
}