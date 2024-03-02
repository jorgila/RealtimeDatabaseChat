package com.estholon.realtimedatabasechat.data.networkDB.dto

data class MessageDto (
    val msg: String,
    val hour: String,
    val date: String,
    val user: UserDto
)

data class UserDto(
    val username: String,
    val admin: Boolean
)