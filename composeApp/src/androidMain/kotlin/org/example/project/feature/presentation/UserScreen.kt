package org.example.project.feature.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.example.project.feature.viewmodel.FriendListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.common.presentation.components.FriendListComponent

@Composable
fun UserScreen(networkViewModel: FriendListViewModel = viewModel()) {
    val friendListState by networkViewModel.friendListState.collectAsState()

    when (friendListState) {
        is FriendListViewModel.FriendListState.Success -> {
            FriendListComponent(componentState = (friendListState as FriendListViewModel.FriendListState.Success).contactsState)
        }

        is FriendListViewModel.FriendListState.Error -> TODO()
        FriendListViewModel.FriendListState.Loading -> {}
    }
}
