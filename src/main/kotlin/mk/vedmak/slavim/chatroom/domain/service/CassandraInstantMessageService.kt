package mk.vedmak.slavim.chatroom.domain.service

import mk.vedmak.slavim.chatroom.domain.model.ChatRoom
import mk.vedmak.slavim.chatroom.domain.model.InstantMessage
import mk.vedmak.slavim.chatroom.domain.repository.ChatRoomRepository
import mk.vedmak.slavim.chatroom.domain.repository.InstantMessageRepository
import org.springframework.stereotype.Service

@Service
class CassandraInstantMessageService(

    val instantMessageRepository: InstantMessageRepository,

    val chatRoomRepository: ChatRoomRepository,

    ):InstantMessageService {

    override fun appendInstantMessageToConversations(instantMessage: InstantMessage) {
        if (instantMessage.isFromAdmin() || instantMessage.isPublic()) {
            val chatRoom: ChatRoom = chatRoomRepository.findById(instantMessage.chatRoomId).get()
            chatRoom.connectedUsers.forEach { connectedUser ->
                instantMessage.username = connectedUser.username
                instantMessageRepository.save(instantMessage)
            }
        } else {
            instantMessage.username = instantMessage.fromUser
            instantMessageRepository.save(instantMessage)
            instantMessage.username = instantMessage.toUser ?: ""
            instantMessageRepository.save(instantMessage)
        }
    }

    override fun findAllInstantMessagesFor(username: String, chatRoomId: String): List<InstantMessage> {
        return instantMessageRepository.findInstantMessagesByUsernameAndChatRoomId(username, chatRoomId)
    }

}