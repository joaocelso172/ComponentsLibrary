package org.example.project.feature.presentation.state

import org.example.project.common.presentation.components.contactlist.ContactsComponentRender

sealed class ContactsComponentState {

    data object Loading : ContactsComponentState()
    data class Success(val contactsRender: ContactsComponentRender) : ContactsComponentState()
    data class Error(val message: String) : ContactsComponentState()
}