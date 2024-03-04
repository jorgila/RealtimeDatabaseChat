package com.estholon.realtimedatabasechat.domain.useCases

import com.estholon.realtimedatabasechat.domain.DatabaseService
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val databaseService: DatabaseService) {

    suspend operator fun invoke(){
        databaseService.clear()
    }
}