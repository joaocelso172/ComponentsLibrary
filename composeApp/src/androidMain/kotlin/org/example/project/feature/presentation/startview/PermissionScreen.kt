package org.example.project.feature.presentation.startview

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.common.presentation.components.featurelist.FeatureItem
import org.example.project.common.presentation.components.featurelist.FeatureItemComponentState
import org.example.project.feature.viewmodel.PermissionViewModel

@Composable
fun PermissionScreen(permissionViewModel: PermissionViewModel = viewModel()) {
    val featureListState by permissionViewModel.featureListState.collectAsState()

    when (featureListState) {
        is FeatureItemComponentState.Granted -> {
            FeatureItem(
                startIcon = Icons.Filled.Person,
                text = "(11) 9999-9999",
                trailingIcon = Icons.Filled.Call,
                startIconTint = Color.Green,
                modifier = Modifier,
                trailingIconTint = Color.Blue)
        }

        is FeatureItemComponentState.Denied -> {
//            Button(onClick = { permissionViewModel.requestPermission() }) {
//                Text("Conceder Permissão")
//            }
        }

        FeatureItemComponentState.Unknown -> TODO()
    }
}