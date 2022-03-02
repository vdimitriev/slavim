package mk.vedmak.slavim.chatroom.domain.model

import java.util.*

data class ChatRoomUser(

    val username: String,

    val joinedAt: Date = Date())

    : Comparable<ChatRoomUser> {

    override fun compareTo(other: ChatRoomUser): Int {
        return username.compareTo(other.username)
    }

    constructor():this("")
}
