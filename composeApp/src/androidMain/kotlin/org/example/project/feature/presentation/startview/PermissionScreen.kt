package org.example.project.feature.presentation.startview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.common.presentation.components.featurelist.FeatureItem
import org.example.project.feature.presentation.state.FeatureItemComponentState
import org.example.project.feature.viewmodel.PermissionViewModel

@Composable
fun PermissionScreen(permissionViewModel: PermissionViewModel = viewModel()) {
    val featureListState by permissionViewModel.featureListState.collectAsState()

    when (featureListState) {
        is FeatureItemComponentState.Granted -> {
            FeatureItem(render = (featureListState as FeatureItemComponentState.Granted).featureItemRender)
    }

        is FeatureItemComponentState.Denied -> {
            FeatureItem(render = (featureListState as FeatureItemComponentState.Denied).featureItemRender)
        }

        FeatureItemComponentState.Unknown -> {

        }
    }

}