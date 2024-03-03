package com.estholon.realtimedatabasechat.data.networkDB.response

import com.estholon.realtimedatabasechat.domain.model.MessageModel
import com.estholon.realtimedatabasechat.domain.model.UserModel

data class MessageResponse(
    val msg: String? = null,
    val hour: String? = null,
    val date: String? = null,
    val user: UserResponse? = null,
) {
    fun toDomain(): MessageModel {

        return MessageModel(
            msg = msg.orEmpty(),
            hour = hour ?: "no date",
            date = date.orEmpty(),
            user = UserModel(username = user?.username.orEmpty(), admin = user?.admin ?: false)
        )
    }
}

data class UserResponse(
    val username: String? = null,
    val admin: Boolean? = null
)