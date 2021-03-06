package mk.vedmak.slavim.authentication.domain.model

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id
    @SequenceGenerator(name = "users_id_sequence", sequenceName = "users_id_seq")
    @GeneratedValue(generator = "users_id_sequence")
    var id: Long? = null,

    var username: String = "",

    var password: String = "",

    var name: String = "",

    var email: String = "",

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")])
    var roles: MutableList<Role> = mutableListOf()

) {

    fun addRoles(roles: MutableList<Role>) {
        this.roles.addAll(roles)
    }

}