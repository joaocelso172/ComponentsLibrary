package org.example.project.feature.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.domain.INetworkRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.common.presentation.components.FriendListComponentState
import org.example.project.domain.NetworkRepository

class FriendListViewModel : ViewModel() {
    private val userRepository: INetworkRepository = NetworkRepository()
    private val _friendListState = MutableStateFlow<FriendListState>(FriendListState.Loading)
    val friendListState: StateFlow<FriendListState> = _friendListState.asStateFlow()

    init {
        fetchContacts()
    }

    private fun fetchContacts(){
        viewModelScope.launch {
            runCatching {
                delay(1500)
                _friendListState.value = FriendListState.Success(contactsState = FriendListComponentState(headerText = "Selecione o contato", userRepository.getContacts() ))
            }.onFailure {
                _friendListState.value = FriendListState.Error( "Falha ao carregar contatos" )
            }
        }
    }

    sealed class FriendListState {

        object Loading : FriendListState()
        data class Success(val contactsState: FriendListComponentState) : FriendListState()
        data class Error(val message: String) : FriendListState()
    }
}