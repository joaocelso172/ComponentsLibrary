package org.example.project.feature.ui.componentstates

import org.example.project.common.components.components.contactlist.ContactsComponentRender

sealed class ContactsComponentState {

    data object Loading : ContactsComponentState()
    data class Success(val contactsRender: ContactsComponentRender) : ContactsComponentState()
    data class Error(val message: String) : ContactsComponentState()
}