package mk.vedmak.slavim.authentication.domain.service

import mk.vedmak.slavim.authentication.domain.model.User

interface UserService {
    fun createUser(user: User): User
}