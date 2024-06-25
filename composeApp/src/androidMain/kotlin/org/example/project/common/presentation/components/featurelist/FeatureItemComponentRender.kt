package org.example.project.common.presentation.components.featurelist

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

open class FeatureItemComponentRender (
    val startIcon: ImageVector,
    val text: String,
    val trailingIcon: ImageVector,
    val textColor: Color = Color.Black,
    val iconSize: Dp = 24.dp
)