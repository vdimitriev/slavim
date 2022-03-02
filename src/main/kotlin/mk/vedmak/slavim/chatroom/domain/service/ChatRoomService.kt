package mk.vedmak.slavim.chatroom.domain.service

import mk.vedmak.slavim.chatroom.domain.model.ChatRoom
import mk.vedmak.slavim.chatroom.domain.model.ChatRoomUser
import mk.vedmak.slavim.chatroom.domain.model.InstantMessage

interface ChatRoomService {

    fun save(chatRoom: ChatRoom): ChatRoom
    fun findById(chatRoomId: String): ChatRoom
    fun join(joiningUser: ChatRoomUser, chatRoom: ChatRoom): ChatRoom
    fun leave(leavingUser: ChatRoomUser, chatRoom: ChatRoom): ChatRoom
    fun sendPublicMessage(instantMessage: InstantMessage)
    fun sendPrivateMessage(instantMessage: InstantMessage)
    fun findAll(): List<ChatRoom>

}