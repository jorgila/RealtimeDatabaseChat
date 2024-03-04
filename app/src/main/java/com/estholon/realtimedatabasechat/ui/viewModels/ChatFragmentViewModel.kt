package com.estholon.realtimedatabasechat.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estholon.realtimedatabasechat.domain.model.MessageModel
import com.estholon.realtimedatabasechat.domain.useCases.GetMessagesUseCase
import com.estholon.realtimedatabasechat.domain.useCases.GetUserNameUseCase
import com.estholon.realtimedatabasechat.domain.useCases.LogoutUseCase
import com.estholon.realtimedatabasechat.domain.useCases.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatFragmentViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    var name : String = ""

    init {
        getUserName()
        getMessage()
    }

    private fun getUserName() {
        viewModelScope.launch(Dispatchers.IO) {
            name = getUserNameUseCase()
        }
    }

    private var _messageList = MutableStateFlow<List<MessageModel>>(emptyList())
    val messageList : StateFlow<List<MessageModel>> = _messageList

    private fun getMessage(){
        viewModelScope.launch {
            getMessagesUseCase().collect() {
                _messageList.value = it
            }
        }
    }

    fun sendMessage(msg: String) {
        val msg = msg
        sendMessageUseCase(msg, name)
    }

    fun logout(onViewFinished:()->Unit) {
        viewModelScope.launch {
           async { logoutUseCase() }.await()
            onViewFinished()
        }
    }

}