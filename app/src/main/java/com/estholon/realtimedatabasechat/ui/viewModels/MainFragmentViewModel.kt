package com.estholon.realtimedatabasechat.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estholon.realtimedatabasechat.domain.useCases.GetUserNameUseCase
import com.estholon.realtimedatabasechat.domain.useCases.SaveUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val saveUserNameUseCase: SaveUserNameUseCase,
    private val getUserNameUseCase: GetUserNameUseCase
): ViewModel() {

    init{
        verifyUserLogged()
    }

    private var _uiState = MutableStateFlow<MainViewState>(MainViewState.LOADING)
    val uiState : StateFlow<MainViewState> = _uiState

    private fun verifyUserLogged() {
        viewModelScope.launch {
            val name = async { getUserNameUseCase() }.await()
            if(name.isNotEmpty()){
                _uiState.value = MainViewState.REGISTERED
            } else {
                _uiState.value = MainViewState.UNREGISTERED
            }
        }
    }

    fun saveUserName(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserNameUseCase(userName)
        }
    }
}

sealed class MainViewState {
    object LOADING: MainViewState()
    object UNREGISTERED: MainViewState()
    object REGISTERED: MainViewState()
}