package mk.vedmak.slavim.chatroom.domain.model

import mk.vedmak.slavim.utils.SystemUsers

class InstantMessageBuilder {

    private lateinit var instantMessage: InstantMessage
    private lateinit var instantMessageChatRoom: InstantMessageChatRoom
    private lateinit var instantMessageType: InstantMessageType
    private lateinit var instantMessageText: InstantMessageText
    private lateinit var instantMessageFromUser: InstantMessageFromUser
    private lateinit var instantMessageToUser: InstantMessageToUser

    fun newMessage(): InstantMessageChatRoom {
        instantMessage = InstantMessage()
        instantMessageChatRoom = InstantMessageChatRoom()
        return instantMessageChatRoom
    }

    inner class InstantMessageType {

        fun systemMessage(): InstantMessageText {
            instantMessage.fromUser = SystemUsers.ADMIN.username
            instantMessageText = InstantMessageText()
            return instantMessageText
        }

        fun publicMessage(): InstantMessageFromUser {
            instantMessage.toUser = null
            instantMessageFromUser = InstantMessageFromUser()
            return instantMessageFromUser
        }

        fun privateMessage(): InstantMessageToUser {
            instantMessageToUser = InstantMessageToUser()
            return instantMessageToUser
        }

    }

    inner class InstantMessageToUser {

        fun toUser(username: String): InstantMessageFromUser {
            instantMessage.toUser = username
            instantMessageFromUser = InstantMessageFromUser()
            return instantMessageFromUser
        }
    }

    inner class InstantMessageFromUser {

        fun fromUser(username: String): InstantMessageText {
            instantMessage.fromUser = username
            instantMessageText = InstantMessageText()
            return instantMessageText
        }
    }

    inner class InstantMessageText {

        fun withText(text: String): InstantMessage {
            instantMessage.text = text
            return instantMessage
        }
    }

    inner class InstantMessageChatRoom {

        fun withChatRoomId(chatRoomId: String): InstantMessageType {
            instantMessage.chatRoomId = chatRoomId
            instantMessageType = InstantMessageType()
            return instantMessageType
        }
    }
}
