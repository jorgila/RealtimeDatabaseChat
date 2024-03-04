package com.estholon.realtimedatabasechat.domain.useCases

import com.estholon.realtimedatabasechat.domain.DatabaseService
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(private val databaseService: DatabaseService){

    suspend operator fun invoke():String = databaseService.getUserName().first()

}