package com.estholon.realtimedatabasechat.data.networkDB

import com.estholon.realtimedatabasechat.data.networkDB.dto.MessageDto
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseChatService @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val PATH = "messages"
    }

    fun sendMsgToFirebase(messageDto: MessageDto){
        val newMsg = reference.child(PATH).push()
        newMsg.setValue(messageDto)
    }

}