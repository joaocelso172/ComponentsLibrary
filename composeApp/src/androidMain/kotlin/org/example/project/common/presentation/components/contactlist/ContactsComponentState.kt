package org.example.project.common.presentation.components.contactlist

sealed class ContactsComponentState {

    data object Loading : ContactsComponentState()
    data class Success(val contactsRender: ContactsComponentRender) : ContactsComponentState()
    data class Error(val message: String) : ContactsComponentState()
}