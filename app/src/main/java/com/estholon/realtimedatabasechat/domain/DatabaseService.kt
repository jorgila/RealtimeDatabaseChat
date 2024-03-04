package com.estholon.realtimedatabasechat.domain

import kotlinx.coroutines.flow.Flow

interface DatabaseService {

    suspend fun saveUserName(userName: String)
    fun getUserName() : Flow<String>
    suspend fun clear()

}