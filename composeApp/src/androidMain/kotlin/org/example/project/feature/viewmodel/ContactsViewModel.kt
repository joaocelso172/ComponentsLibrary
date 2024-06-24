package org.example.project.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.domain.repository.IContactsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.common.presentation.components.contactlist.ContactsComponentRender
import org.example.project.common.presentation.components.contactlist.ContactsComponentState

class ContactsViewModel(private val repository: IContactsRepository) : ViewModel() {
    private val _contactsComponentState = MutableStateFlow<ContactsComponentState>(ContactsComponentState.Loading)
    val contactsComponentState: StateFlow<ContactsComponentState> = _contactsComponentState.asStateFlow()

    init {
        fetchContacts()
    }

    private fun fetchContacts(){
        viewModelScope.launch {
            runCatching {
                delay(1500)
                _contactsComponentState.value = ContactsComponentState.Success(contactsRender = ContactsComponentRender(headerText = "Selecione o contato", repository.getContacts() ))
            }.onFailure {
                _contactsComponentState.value = ContactsComponentState.Error( "Falha ao carregar contatos" )
            }
        }
    }

}