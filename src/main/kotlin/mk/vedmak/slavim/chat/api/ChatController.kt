package mk.vedmak.slavim.chat.api

import mk.vedmak.slavim.chatroom.domain.model.ChatRoom
import mk.vedmak.slavim.chatroom.domain.service.ChatRoomService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class ChatController(val chatRoomService: ChatRoomService) {

    @RequestMapping("/chat")
    fun getRooms(): ModelAndView? {
        val modelAndView = ModelAndView("chat")
        val chatRooms: List<ChatRoom> = chatRoomService.findAll()
        modelAndView.addObject("chatRooms", chatRooms)
        return modelAndView
    }

}