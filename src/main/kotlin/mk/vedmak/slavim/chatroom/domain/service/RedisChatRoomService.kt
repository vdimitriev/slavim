package mk.vedmak.slavim.chatroom.domain.service

import mk.vedmak.slavim.chatroom.domain.model.ChatRoom
import mk.vedmak.slavim.chatroom.domain.model.ChatRoomUser
import mk.vedmak.slavim.chatroom.domain.model.InstantMessage
import mk.vedmak.slavim.chatroom.domain.repository.ChatRoomRepository
import mk.vedmak.slavim.utils.Destinations
import mk.vedmak.slavim.utils.SystemMessages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class RedisChatRoomService(

    val webSocketMessagingTemplate: SimpMessagingTemplate,

    val chatRoomRepository: ChatRoomRepository,

    val instantMessageService: InstantMessageService

    ):ChatRoomService {

    override fun save(chatRoom: ChatRoom): ChatRoom {
        return chatRoomRepository.save(chatRoom)
    }

    override fun findById(chatRoomId: String): ChatRoom {
        return chatRoomRepository.findById(chatRoomId).get()
    }

    override fun join(joiningUser: ChatRoomUser, chatRoom: ChatRoom): ChatRoom {
        chatRoom.addUser(joiningUser)
        chatRoomRepository.save(chatRoom)
        sendPublicMessage(SystemMessages.welcome(chatRoom.id, joiningUser.username))
        updateConnectedUsersViaWebSocket(chatRoom)
        return chatRoom
    }

    override fun leave(leavingUser: ChatRoomUser, chatRoom: ChatRoom): ChatRoom {
        sendPublicMessage(SystemMessages.goodbye(chatRoom.id, leavingUser.username))
        chatRoom.removeUser(leavingUser)
        chatRoomRepository.save(chatRoom)
        updateConnectedUsersViaWebSocket(chatRoom)
        return chatRoom
    }

    override fun sendPublicMessage(instantMessage: InstantMessage) {
        webSocketMessagingTemplate.convertAndSend(
            Destinations.ChatRoom.publicMessages(instantMessage.chatRoomId),
            instantMessage
        )
        instantMessageService.appendInstantMessageToConversations(instantMessage)
    }

    override fun sendPrivateMessage(instantMessage: InstantMessage) {
        webSocketMessagingTemplate.convertAndSendToUser(
            instantMessage.toUser ?: "",
            Destinations.ChatRoom.privateMessages(instantMessage.chatRoomId),
            instantMessage
        )
        webSocketMessagingTemplate.convertAndSendToUser(
            instantMessage.fromUser,
            Destinations.ChatRoom.privateMessages(instantMessage.chatRoomId),
            instantMessage
        )
        instantMessageService.appendInstantMessageToConversations(instantMessage)
    }

    override fun findAll(): List<ChatRoom> {
        return chatRoomRepository.findAll() as List<ChatRoom>
    }


    private fun updateConnectedUsersViaWebSocket(chatRoom: ChatRoom) {
        webSocketMessagingTemplate.convertAndSend(
            Destinations.ChatRoom.connectedUsers(chatRoom.id),
            chatRoom.connectedUsers
        )
    }

}