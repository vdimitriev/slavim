package mk.vedmak.slavim.chatroom.domain.model

import java.util.*

class ChatRoomUser(

    var username: String,
    var joinedAt: Date = Date()

    ): Comparable<ChatRoomUser> {

    override fun compareTo(other: ChatRoomUser): Int {
        return username.compareTo(other.username)
    }

    override fun hashCode(): Int {
        return 31 + username.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (javaClass != other.javaClass) return false
        if (username != (other as ChatRoomUser).username) return false
        return true
    }

    override fun toString(): String {
        return username
    }
}
