package com.estholon.realtimedatabasechat.ui.viewModels

import androidx.lifecycle.ViewModel
import com.estholon.realtimedatabasechat.domain.useCases.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatFragmentViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {

    fun sendMessage(){
        val msg = "Holiwi"
        sendMessageUseCase(msg)
    }

}