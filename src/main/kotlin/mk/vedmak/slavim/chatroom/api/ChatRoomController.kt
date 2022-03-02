package mk.vedmak.slavim.chatroom.api

import mk.vedmak.slavim.chatroom.domain.model.ChatRoom
import mk.vedmak.slavim.chatroom.domain.model.ChatRoomUser
import mk.vedmak.slavim.chatroom.domain.model.InstantMessage
import mk.vedmak.slavim.chatroom.domain.service.ChatRoomService
import mk.vedmak.slavim.chatroom.domain.service.InstantMessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.annotation.SubscribeMapping
import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import java.security.Principal

@Controller
class ChatRoomController(

    val chatRoomService: ChatRoomService,

    val instantMessageService: InstantMessageService) {

    @Secured("ROLE_ADMIN")
    @RequestMapping(path = ["/chatroom"], method = [RequestMethod.POST])
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    fun createChatRoom(@RequestBody chatRoom: ChatRoom): ChatRoom {
        return chatRoomService.save(chatRoom)
    }

    @RequestMapping("/chatroom/{chatRoomId}")
    fun join(@PathVariable chatRoomId: String, principal: Principal): ModelAndView {
        val modelAndView = ModelAndView("chatroom")
        modelAndView.addObject("chatRoom", chatRoomService.findById(chatRoomId))
        return modelAndView
    }

    @SubscribeMapping("/connected.users")
    fun listChatRoomConnectedUsersOnSubscribe(headerAccessor: SimpMessageHeaderAccessor): List<ChatRoomUser> {
        val chatRoomId = headerAccessor.sessionAttributes!!["chatRoomId"].toString()
        return chatRoomService.findById(chatRoomId).connectedUsers
    }

    @SubscribeMapping("/old.messages")
    fun listOldMessagesFromUserOnSubscribe(principal: Principal,headerAccessor: SimpMessageHeaderAccessor): List<InstantMessage> {
        val chatRoomId = headerAccessor.sessionAttributes!!["chatRoomId"].toString()
        return instantMessageService.findAllInstantMessagesFor(principal.name, chatRoomId)
    }

    @MessageMapping("/send.message")
    fun sendMessage(@Payload instantMessage: InstantMessage, principal: Principal,headerAccessor: SimpMessageHeaderAccessor) {
        val chatRoomId = headerAccessor.sessionAttributes!!["chatRoomId"].toString()
        instantMessage.fromUser = principal.name
        instantMessage.chatRoomId = chatRoomId
        if (instantMessage.isPublic()) {
            chatRoomService.sendPublicMessage(instantMessage)
        } else {
            chatRoomService.sendPrivateMessage(instantMessage)
        }
    }

}