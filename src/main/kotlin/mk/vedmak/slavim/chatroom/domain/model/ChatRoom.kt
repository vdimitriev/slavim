package mk.vedmak.slavim.chatroom.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.util.*

@RedisHash("chatrooms")
data class ChatRoom(

    @Id
    val id: String,
    val name: String,
    val description: String,
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