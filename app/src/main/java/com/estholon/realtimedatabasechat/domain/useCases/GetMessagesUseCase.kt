package com.estholon.realtimedatabasechat.domain.useCases

import com.estholon.realtimedatabasechat.data.networkDB.FirebaseChatService
import javax.inject.Inject


class GetMessagesUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {

    operator fun invoke() = firebaseChatService.getMessages()

}