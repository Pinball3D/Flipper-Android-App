package com.flipperdevices.keyscreen.impl.composable.actions

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.flipperdevices.core.ui.ktx.painterResourceByKey
import com.flipperdevices.core.ui.theme.LocalPallet
import com.flipperdevices.core.ui.theme.LocalTypography

@Composable
fun ComposableActionRow(
    @DrawableRes iconId: Int,
    tint: Color = LocalPallet.current.iconTint100,
    @StringRes descriptionId: Int,
    descriptionColor: Color = LocalPallet.current.text100,
    onClick: () -> Unit
) {
    ComposableActionRowInternal(
        iconId = iconId,
        tint = tint,
        descriptionId = descriptionId,
        descriptionColor = descriptionColor,
        onClick = onClick
    )
}

@Composable
fun ComposableActionRowInProgress(
    @StringRes descriptionId: Int,
    descriptionColor: Color = LocalPallet.current.text100
) {
    ComposableActionRowInternal(
        iconId = null,
        descriptionId = descriptionId,
        descriptionColor = descriptionColor,
        isProgress = true,
        onClick = null
    )
}

@Composable
private fun ComposableActionRowInternal(
    @DrawableRes iconId: Int? = null,
    tint: Color = LocalPallet.current.iconTint100,
    @StringRes descriptionId: Int,
    descriptionColor: Color = LocalPallet.current.text100,
    isProgress: Boolean = false,
    onClick: (() -> Unit)?
) {
    var modifierForRow = Modifier
        .fillMaxWidth()

    if (!isProgress && onClick != null) {
        modifierForRow = modifierForRow.clickable(
            onClick = onClick,
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple()
        )
    }
    modifierForRow = modifierForRow
        .padding(horizontal = 24.dp, vertical = 10.dp)

    Row(
        modifier = modifierForRow,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ComposableActionContent(iconId, tint, descriptionId, descriptionColor, isProgress)
    }
}

@Composable
private fun ComposableActionContent(
    @DrawableRes iconId: Int? = null,
    tint: Color = LocalPallet.current.iconTint100,
    @StringRes descriptionId: Int,
    descriptionColor: Color = LocalPallet.current.text100,
    isProgress: Boolean = false
) {
    val descriptionText = stringResource(descriptionId)

    if (isProgress) {
        CircularProgressIndicator(
            modifier = Modifier.size(size = 24.dp)
        )
    } else {
        Icon(
            modifier = Modifier.size(size = 24.dp),
            painter = painterResourceByKey(iconId!!),
            contentDescription = descriptionText,
            tint = tint
        )
    }

    Text(
        modifier = Modifier.padding(start = 10.dp),
        text = stringResource(descriptionId),
        color = descriptionColor,
        style = LocalTypography.current.buttonM16
    )
}
