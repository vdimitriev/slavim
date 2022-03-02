package mk.vedmak.slavim.authentication.domain.model

import javax.persistence.*

@Entity
@Table(name = "role")
data class Role(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    var id: Int? = null,

    var name: String = ""

)