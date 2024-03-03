package com.estholon.realtimedatabasechat.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estholon.realtimedatabasechat.domain.model.MessageModel
import com.estholon.realtimedatabasechat.domain.useCases.GetMessagesUseCase
import com.estholon.realtimedatabasechat.domain.useCases.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatFragmentViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
) : ViewModel() {

    init {
        getMessage()
    }

    var messageList = MutableStateFlow<List<MessageModel>>(emptyList())

    fun getMessage(){
        viewModelScope.launch {
            getMessagesUseCase().collect() {
                Log.d("ViewModel",it.toString())
                messageList.value = it
            }
        }
    }

    fun sendMessage(){
        val msg = "Holiwi"
        sendMessageUseCase(msg)
    }

}