package com.estholon.realtimedatabasechat.domain.model

data class MessageModel (
    val msg: String,
    val hour: String,
    val date: String,
    val user: UserModel
)

data class UserModel(
    val username: String,
    val admin: Boolean
)