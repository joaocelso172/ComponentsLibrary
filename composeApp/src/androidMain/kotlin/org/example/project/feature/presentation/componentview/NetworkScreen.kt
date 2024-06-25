package org.example.project.feature.presentation.componentview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.example.project.feature.viewmodel.ContactsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.common.presentation.components.contactlist.ContactsComponent
import org.example.project.feature.presentation.state.ContactsComponentState
import org.example.project.common.presentation.components.contactlist.LoadingColumnList

@Composable
fun NetworkScreen(componentViewModel: ContactsViewModel = viewModel()) {
    val friendListState by componentViewModel.contactsComponentState.collectAsState()

    when (friendListState) {
        is ContactsComponentState.Success -> {
            ContactsComponent(render = (friendListState as ContactsComponentState.Success).contactsRender)
        }

        is ContactsComponentState.Error -> {

        }

        ContactsComponentState.Loading -> {
            LoadingColumnList()
        }
    }
}
