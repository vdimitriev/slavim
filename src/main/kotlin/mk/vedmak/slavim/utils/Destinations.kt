package mk.vedmak.slavim.utils

class Destinations {

    object ChatRoom {

        fun publicMessages(chatRoomId: String): String {
            return "/topic/$chatRoomId.public.messages"
        }

        fun privateMessages(chatRoomId: String): String {
            return "/queue/$chatRoomId.private.messages"
        }

        fun connectedUsers(chatRoomId: String): String {
            return "/topic/$chatRoomId.connected.users"
        }
    }
}
