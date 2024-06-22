package org.example.project.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.domain.IContactsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.common.presentation.components.ContactsComponentRender
import org.example.project.domain.ContactsRepository

class ContactsViewModel : ViewModel() {
    private val contactsRepository: IContactsRepository = ContactsRepository()
    private val _contactsState = MutableStateFlow<ContactsState>(ContactsState.Loading)
    val contactsState: StateFlow<ContactsState> = _contactsState.asStateFlow()

    init {
        fetchContacts()
    }

    private fun fetchContacts(){
        viewModelScope.launch {
            runCatching {
                delay(1500)
                _contactsState.value = ContactsState.Success(contactsRender = ContactsComponentRender(headerText = "Selecione o contato", contactsRepository.getContacts() ))
            }.onFailure {
                _contactsState.value = ContactsState.Error( "Falha ao carregar contatos" )
            }
        }
    }

    sealed class ContactsState {

        object Loading : ContactsState()
        data class Success(val contactsRender: ContactsComponentRender) : ContactsState()
        data class Error(val message: String) : ContactsState()
    }
}