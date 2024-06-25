package org.example.project.feature.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.project.common.presentation.components.featurelist.FeatureItemComponentRender
import org.example.project.feature.presentation.state.FeatureItemComponentState
import org.example.project.feature.domain.usecase.CheckPermissionUseCase
import org.example.project.feature.domain.usecase.RequestPermissionUseCase

class PermissionViewModel(
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val requestPermissionUseCase: RequestPermissionUseCase
) : ViewModel() {
    private val _featureListState = MutableStateFlow<FeatureItemComponentState>(
        FeatureItemComponentState.Unknown)
    val featureListState: StateFlow<FeatureItemComponentState> = _featureListState.asStateFlow()
    val iconStatus: ImageVector
        get() = when (featureListState.value) {
        is FeatureItemComponentState.Granted -> Icons.Filled.TaskAlt
        is FeatureItemComponentState.Denied -> Icons.Filled.Error
        else -> Icons.Filled.Warning
    }

    init {
        checkPermission()
    }

    fun checkPermission() {
        _featureListState.value = if (checkPermissionUseCase()) {
            FeatureItemComponentState.Granted(FeatureItemComponentRender(
                startIcon = Icons.Filled.Contacts,
                text = "Contatos",
                trailingIcon = iconStatus
            ))
        } else {
            FeatureItemComponentState.Denied(
                FeatureItemComponentRender(
                    startIcon = Icons.Filled.Contacts,
                    text = "Contatos",
                    trailingIcon = iconStatus
                ))
        }
    }

    fun requestPermission() {
        requestPermissionUseCase()
        // Após a solicitação, verifique a permissão novamente e atualize o permissionState
    }
}