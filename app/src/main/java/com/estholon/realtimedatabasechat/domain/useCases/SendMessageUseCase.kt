package com.estholon.realtimedatabasechat.domain.useCases

import com.estholon.realtimedatabasechat.data.networkDB.FirebaseChatService
import com.estholon.realtimedatabasechat.data.networkDB.dto.MessageDto
import com.estholon.realtimedatabasechat.data.networkDB.dto.UserDto
import java.util.Calendar
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {

    operator fun invoke(msg:String){

        val calendar = Calendar.getInstance()

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)
        val completeHour = String.format("%02d:%02d",hour,minutes)

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val completeDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)

        val userDto = UserDto("Prueba",false)

        val messageDto = MessageDto(
            msg = msg,
            hour = completeHour,
            date = completeDate,
            user = userDto,
        )

        firebaseChatService.sendMsgToFirebase(messageDto)
    }

}