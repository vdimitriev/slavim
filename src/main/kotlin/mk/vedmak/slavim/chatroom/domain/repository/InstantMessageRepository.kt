package mk.vedmak.slavim.chatroom.domain.repository

import mk.vedmak.slavim.chatroom.domain.model.InstantMessage
import org.springframework.data.cassandra.repository.CassandraRepository

interface InstantMessageRepository:CassandraRepository<InstantMessage, String> {

    fun findInstantMessagesByUsernameAndChatRoomId(username: String, chatRoomId: String): List<InstantMessage>

}