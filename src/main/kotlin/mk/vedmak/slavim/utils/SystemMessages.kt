package mk.vedmak.slavim.utils

import mk.vedmak.slavim.chatroom.domain.model.InstantMessage
import mk.vedmak.slavim.chatroom.domain.model.InstantMessageBuilder

object SystemMessages {

    fun welcome(chatRoomId: String, username: String): InstantMessage {
        return InstantMessageBuilder()
            .newMessage()
            .withChatRoomId(chatRoomId)
            .systemMessage()
            .withText("$username joined us :)")
    }

    fun goodbye(chatRoomId: String, username: String): InstantMessage {
        return InstantMessageBuilder()
            .newMessage()
            .withChatRoomId(chatRoomId)
            .systemMessage()
            .withText("$username left us :(")
    }
}
