package mk.vedmak.slavim.chatroom.websocket

import mk.vedmak.slavim.chatroom.domain.model.ChatRoomUser
import mk.vedmak.slavim.chatroom.domain.service.ChatRoomService
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent

@Component
class WebSocketEvents(val chatRoomService: ChatRoomService) {

    @EventListener
    fun handleSessionConnected(event: SessionConnectEvent) {
        val headers = SimpMessageHeaderAccessor.wrap(event.message)
        val chatRoomId = headers.getNativeHeader("chatRoomId")!![0]
        headers.sessionAttributes!!["chatRoomId"] = chatRoomId
        val joiningUser = ChatRoomUser(event.user!!.name)
        chatRoomService.join(joiningUser, chatRoomService.findById(chatRoomId))
    }

    @EventListener
    fun handleSessionDisconnect(event: SessionDisconnectEvent) {
        val headers = SimpMessageHeaderAccessor.wrap(event.message)
        val chatRoomId = headers.sessionAttributes!!["chatRoomId"].toString()
        val leavingUser = ChatRoomUser(event.user!!.name)
        chatRoomService.leave(leavingUser, chatRoomService.findById(chatRoomId))
    }

}