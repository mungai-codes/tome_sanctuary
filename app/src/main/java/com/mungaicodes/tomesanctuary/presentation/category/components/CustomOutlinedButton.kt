package com.mungaicodes.tomesanctuary.presentation.category.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungaicodes.tomesanctuary.presentation.ui.theme.LampLight
import com.mungaicodes.tomesanctuary.presentation.ui.theme.StatusBar

@Composable
fun CustomOutlinedButton(
    onClick: () -> Unit,
    customOutlinedButton: CustomOutlinedButton,
) {
    OutlinedButton(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = LampLight),
        border = BorderStroke(1.dp, StatusBar),
        enabled = customOutlinedButton.isEnabled
    ) {
        Icon(
            imageVector = customOutlinedButton.icon,
            contentDescription = customOutlinedButton.description
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(
            text = customOutlinedButton.buttonLabel.uppercase(),
            fontSize = 8.sp
        )
    }
}
