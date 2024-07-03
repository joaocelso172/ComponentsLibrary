package org.example.project.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.common.components.components.contactlist.ContactsComponentRender
import org.example.project.feature.domain.usecase.GetContactsUseCase
import org.example.project.feature.ui.componentstates.ContactsComponentState
import org.example.project.feature.ui.states.ComponentsCatalogUiState

class ComponentsCatalogViewModel(private val getContactsUseCase: GetContactsUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<ComponentsCatalogUiState>(
        ComponentsCatalogUiState()
    )
    val uiState: StateFlow<ComponentsCatalogUiState> =
        _uiState.asStateFlow()

    init {
        fetchContacts()
    }

    private fun fetchContacts() {
        viewModelScope.launch {
            runCatching {
                delay(1500)
                _uiState.update {
                    ComponentsCatalogUiState(contactsComponentState = ContactsComponentState.Success(
                        contactsRender = ContactsComponentRender(
                            headerText = "Selecione o contato", getContactsUseCase.invoke()
                        )
                    ))
                }
            }.onFailure {
                _uiState.update {
                    ComponentsCatalogUiState(ContactsComponentState.Error("Falha ao carregar contatos"))
                }
            }
        }
    }


}