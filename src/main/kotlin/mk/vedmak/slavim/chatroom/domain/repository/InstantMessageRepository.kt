package mk.vedmak.slavim.chatroom.domain.repository

import mk.vedmak.slavim.chatroom.domain.model.InstantMessage
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface InstantMessageRepository:CassandraRepository<InstantMessage, String> {

    fun findInstantMessagesByUsernameAndChatRoomId(username: String, chatRoomId: String): List<InstantMessage>

}