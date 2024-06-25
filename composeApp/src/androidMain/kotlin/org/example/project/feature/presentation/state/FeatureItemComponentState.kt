package org.example.project.feature.presentation.state

import org.example.project.common.presentation.components.featurelist.FeatureItemComponentRender

sealed class FeatureItemComponentState {
    data object Unknown :
        FeatureItemComponentState()
    data class Granted(val featureItemRender: FeatureItemComponentRender) :
        FeatureItemComponentState()
    data class Denied(val featureItemRender: FeatureItemComponentRender) :
        FeatureItemComponentState()
}