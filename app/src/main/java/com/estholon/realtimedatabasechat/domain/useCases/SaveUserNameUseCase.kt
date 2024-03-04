package com.estholon.realtimedatabasechat.domain.useCases

import com.estholon.realtimedatabasechat.domain.DatabaseService
import javax.inject.Inject

class SaveUserNameUseCase @Inject constructor(private val databaseService: DatabaseService) {

    suspend operator fun invoke(userName: String){
        databaseService.saveUserName(userName)
    }
}