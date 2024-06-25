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
import org.example.project.feature.domain.usecase.GetContactsUseCase
import org.example.project.feature.presentation.state.ContactsComponentState

class ContactsViewModel(private val getContactsUseCase: GetContactsUseCase) : ViewModel() {
    private val _contactsComponentState = MutableStateFlow<ContactsComponentState>(
        ContactsComponentState.Loading)
    val contactsComponentState: StateFlow<ContactsComponentState> = _contactsComponentState.asStateFlow()

    init {
        fetchContacts()
    }

    private fun fetchContacts(){
        viewModelScope.launch {
            runCatching {
                delay(1500)
                _contactsComponentState.value = ContactsComponentState.Success(contactsRender = ContactsComponentRender(
                    headerText = "Selecione o contato",
                    getContactsUseCase.invoke()))
            }.onFailure {
                _contactsComponentState.value = ContactsComponentState.Error( "Falha ao carregar contatos" )
            }
        }
    }



}