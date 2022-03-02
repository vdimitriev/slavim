package mk.vedmak.slavim.chatroom.domain.service

import mk.vedmak.slavim.chatroom.domain.model.InstantMessage

interface InstantMessageService {

    fun appendInstantMessageToConversations(instantMessage: InstantMessage)
    fun findAllInstantMessagesFor(username: String, chatRoomId: String): List<InstantMessage>

}