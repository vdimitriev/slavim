package mk.vedmak.slavim.chatroom.domain.repository

import mk.vedmak.slavim.chatroom.domain.model.ChatRoom
import org.springframework.data.repository.CrudRepository

interface ChatRoomRepository: CrudRepository<ChatRoom, String>