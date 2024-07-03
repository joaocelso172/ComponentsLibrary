package org.example.project.feature.ui.states

import org.example.project.feature.ui.componentstates.ContactsComponentState

data class ComponentsCatalogUiState(
    val contactsComponentState: ContactsComponentState = ContactsComponentState.Loading
)