package com.estholon.realtimedatabasechat.domain.useCases

import com.estholon.realtimedatabasechat.data.networkDB.FirebaseChatService
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {
    operator fun invoke(msg:String){
        firebaseChatService.sendMsgToFirebase(msg)
    }

}