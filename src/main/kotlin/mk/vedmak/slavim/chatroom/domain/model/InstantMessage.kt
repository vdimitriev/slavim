package mk.vedmak.slavim.chatroom.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import mk.vedmak.slavim.utils.SystemUsers
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*

@Table("messages")
data class InstantMessage(

    @PrimaryKeyColumn(name = "username", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @JsonIgnore
    var username: String = "",

    @PrimaryKeyColumn(name = "chatRoomId", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    var chatRoomId: String = "",

    @PrimaryKeyColumn(name = "date", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    var date: Date = Date(),

    var fromUser: String = "",

    var toUser: String? = null,

    var text: String = ""

    ) {

    fun isPublic(): Boolean {
        return toUser.isNullOrEmpty()
    }

    fun isFromAdmin(): Boolean {
        return fromUser == SystemUsers.ADMIN.username
    }

}
