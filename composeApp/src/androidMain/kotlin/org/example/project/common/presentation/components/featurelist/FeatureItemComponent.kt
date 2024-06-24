package org.example.project.common.presentation.components.featurelist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FeatureItem(
    startIcon: ImageVector,
    text: String,
    trailingIcon: ImageVector,
    modifier: Modifier = Modifier,
    startIconModifier: Modifier = Modifier,
    trailingIconModifier: Modifier = Modifier,
    textColor: Color = Color.Black,
    iconSize: Dp = 24.dp,
    startIconTint: Color = LocalContentColor.current,
    trailingIconTint: Color = LocalContentColor.current
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = startIcon,
            contentDescription = null,
            modifier = startIconModifier.size(iconSize).then(Modifier.padding(end = 4.dp)),
            tint = startIconTint)
        Text(
            text = text,
            color = textColor
        )
        Spacer(Modifier.weight(1f))
        Icon(
            imageVector = trailingIcon,
            contentDescription = null,
            modifier = trailingIconModifier.size(iconSize),
            tint = trailingIconTint
        )
    }
}