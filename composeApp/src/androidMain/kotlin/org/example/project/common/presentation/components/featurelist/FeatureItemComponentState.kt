package org.example.project.common.presentation.components.featurelist

sealed class FeatureItemComponentState {
    data object Unknown : FeatureItemComponentState()
    data object Granted : FeatureItemComponentState()
    data object Denied : FeatureItemComponentState()
}