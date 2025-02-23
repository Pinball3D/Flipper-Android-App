package com.flipperdevices.share.receive.composable.screen // ktlint-disable filename

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.flipperdevices.bridge.dao.api.model.parsed.FlipperKeyParsed
import com.flipperdevices.core.ui.ktx.ComposableFlipperButton
import com.flipperdevices.core.ui.ktx.LocalPlaceholder
import com.flipperdevices.keyscreen.api.KeyScreenApi
import com.flipperdevices.share.receive.R
import com.flipperdevices.share.receive.composable.component.ComposableKeySaveBar
import com.flipperdevices.share.receive.composable.component.ComposableKeySaveFooter

@Composable
fun ComposableKeyScreenProgress(
    keyScreenApi: KeyScreenApi,
    onCancel: () -> Unit
) {
    val keyParsed = FlipperKeyParsed.IButton(
        keyName = "Placeholder",
        notes = "Placeholder",
        keyType = "Placeholder",
        data = null
    )

    Column {
        ComposableKeySaveBar(onCancel)
        CompositionLocalProvider(LocalPlaceholder provides true) {
            keyScreenApi.KeyCard(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
                key = keyParsed,
                deleted = false
            )
            ComposableKeySaveFooter {
                ComposableFlipperButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.receive_save_btn),
                    onClick = { }
                )
            }
        }
    }
}
