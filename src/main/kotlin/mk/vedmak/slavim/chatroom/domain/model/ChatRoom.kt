package mk.vedmak.slavim.chatroom.domain.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
@RedisHash("chatrooms")
data class ChatRoom(

    @Id
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var connectedUsers: MutableList<ChatRoomUser> = mutableListOf()

    ) {

    fun addUser(user: ChatRoomUser) {
        connectedUsers.add(user)
    }

    fun removeUser(user: ChatRoomUser) {
        connectedUsers.remove(user)
    }

    fun getNumberOfConnectedUsers(): Int {
        return connectedUsers.size
    }

}
