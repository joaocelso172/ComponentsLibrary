package org.example.project.feature.ui.componentstates

import org.example.project.common.components.components.featurelist.FeatureItemComponentRender

sealed class FeatureItemComponentState {
    data object Unknown :
        FeatureItemComponentState()
    data class Granted(val featureItemRender: FeatureItemComponentRender) :
        FeatureItemComponentState()
    data class Denied(val featureItemRender: FeatureItemComponentRender) :
        FeatureItemComponentState()
}