package org.example.project.feature.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.project.common.presentation.components.featurelist.FeatureItemComponentState
import org.example.project.feature.domain.usecase.CheckPermissionUseCase
import org.example.project.feature.domain.usecase.RequestPermissionUseCase

class PermissionViewModel(
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val requestPermissionUseCase: RequestPermissionUseCase
) : ViewModel() {
    private val _featureListState = MutableStateFlow<FeatureItemComponentState>(FeatureItemComponentState.Unknown)
    val featureListState: StateFlow<FeatureItemComponentState> = _featureListState.asStateFlow()

    init {
        checkPermission()
    }

    fun checkPermission() {
        _featureListState.value = if (checkPermissionUseCase()) {
            FeatureItemComponentState.Granted
        } else {
            FeatureItemComponentState.Denied
        }
    }

    fun requestPermission() {
        requestPermissionUseCase()
        // Após a solicitação, verifique a permissão novamente e atualize o permissionState
    }
}