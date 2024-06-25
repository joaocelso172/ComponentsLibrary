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
import androidx.compose.ui.unit.dp

@Composable
fun FeatureItem(
    render: FeatureItemComponentRender,
    modifier: Modifier = Modifier,
    startIconModifier: Modifier = Modifier,
    trailingIconModifier: Modifier = Modifier,
    startIconTint: Color = LocalContentColor.current,
    trailingIconTint: Color = LocalContentColor.current
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = render.startIcon,
            contentDescription = null,
            modifier = startIconModifier.size(render.iconSize).then(Modifier.padding(end = 4.dp)),
            tint = startIconTint)
        Text(
            text = render.text,
            color = render.textColor

        )
        Spacer(Modifier.weight(1f))
        Icon(
            imageVector = render.trailingIcon,
            contentDescription = null,
            modifier = trailingIconModifier.size(render.iconSize),
            tint = trailingIconTint
        )
    }
}