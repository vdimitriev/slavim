package mk.vedmak.slavim.chatroom.websocket

import mk.vedmak.slavim.chatroom.domain.model.ChatRoomUser
import mk.vedmak.slavim.chatroom.domain.service.ChatRoomService
import mu.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent
import java.util.*

@Component
class WebSocketEvents(val chatRoomService: ChatRoomService) {

    private val logger = KotlinLogging.logger {}

    @EventListener
    fun handleSessionConnected(event: SessionConnectEvent) {

        val headers = SimpMessageHeaderAccessor.wrap(event.message)
        val chatRoomId = headers.getNativeHeader("chatRoomId")!![0]
        headers.sessionAttributes!!["chatRoomId"] = chatRoomId
        val joiningUser = ChatRoomUser(event.user!!.name, Date())

        logger.info("join session for user $joiningUser to room $chatRoomId")
        chatRoomService.join(joiningUser, chatRoomService.findById(chatRoomId))
    }

    @EventListener
    fun handleSessionDisconnect(event: SessionDisconnectEvent) {
        val headers = SimpMessageHeaderAccessor.wrap(event.message)
        val chatRoomId = headers.sessionAttributes!!["chatRoomId"].toString()
        val leavingUser = ChatRoomUser(event.user!!.name, Date())

        logger.info("leave session for user $leavingUser to room $chatRoomId")
        chatRoomService.leave(leavingUser, chatRoomService.findById(chatRoomId))
    }

}