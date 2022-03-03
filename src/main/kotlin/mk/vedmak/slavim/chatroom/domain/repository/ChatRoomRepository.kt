package mk.vedmak.slavim.chatroom.domain.repository

import mk.vedmak.slavim.chatroom.domain.model.ChatRoom
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRoomRepository: CrudRepository<ChatRoom, String>