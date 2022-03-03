package mk.vedmak.slavim.chatroom.domain.model

import java.util.*

data class ChatRoomUser(

    var username: String,
    var joinedAt: Date

    ): Comparable<ChatRoomUser> {

    override fun compareTo(other: ChatRoomUser): Int {
        return username.compareTo(other.username)
    }
}
