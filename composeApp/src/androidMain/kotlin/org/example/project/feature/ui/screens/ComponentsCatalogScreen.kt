package org.example.project.feature.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import org.example.project.feature.viewmodel.ComponentsCatalogViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.common.components.components.contactlist.ContactsComponent
import org.example.project.feature.ui.componentstates.ContactsComponentState
import org.example.project.common.components.components.contactlist.LoadingColumnList
import org.example.project.feature.ui.states.ComponentsCatalogUiState

@Composable
fun ComponentsCatalogScreen(componentViewModel: ComponentsCatalogViewModel = viewModel()) {
    val state = componentViewModel.uiState.collectAsState()

    ContactsComponentSection(state)

}

@Composable
fun ContactsComponentSection(state: State<ComponentsCatalogUiState>) {
    state.value.contactsComponentState.let {
        when (it) {
            is ContactsComponentState.Success -> {
                ContactsComponent(render = it.contactsRender)
            }

            is ContactsComponentState.Error -> {

            }

            ContactsComponentState.Loading -> {
                LoadingColumnList()
            }
        }
    }
}
