package org.example.project.feature.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.example.project.feature.viewmodel.ContactsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.common.presentation.components.ContactsComponent

@Composable
fun UserScreen(networkViewModel: ContactsViewModel = viewModel()) {
    val friendListState by networkViewModel.contactsState.collectAsState()

    when (friendListState) {
        is ContactsViewModel.ContactsState.Success -> {
            ContactsComponent(render = (friendListState as ContactsViewModel.ContactsState.Success).contactsRender)
        }

        is ContactsViewModel.ContactsState.Error -> TODO()
        ContactsViewModel.ContactsState.Loading -> {
            LoadingColumnList()
        }
    }
}
