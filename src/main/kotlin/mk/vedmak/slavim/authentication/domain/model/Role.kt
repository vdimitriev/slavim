package mk.vedmak.slavim.authentication.domain.model

import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(

    @Id
    @SequenceGenerator(name = "roles_id_sequence", sequenceName = "roles_id_seq")
    @GeneratedValue(generator = "roles_id_sequence")
    var id: Long? = null,

    var name: String = "",

    @ManyToMany(mappedBy = "roles", cascade = [CascadeType.ALL])
    var users:MutableList<User> = mutableListOf()

)