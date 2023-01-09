package com.mungaicodes.tomesanctuary.presentation.category.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mungaicodes.tomesanctuary.presentation.ui.theme.GreenGrey50

data class BookExtras(
    val header: String,
)

val bookExtras = listOf(
    BookExtras(
        header = "Publisher"
    ),
    BookExtras(
        header = "Pages"
    ),
    BookExtras(
        header = "Rating"
    ),
    BookExtras(
        header = "Published"
    )
)

@Composable
fun BookExtra(
    modifier: Modifier = Modifier,
    header: BookExtras,
    detail: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = header.header,
            color = GreenGrey50,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Text(
            text = detail,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}
