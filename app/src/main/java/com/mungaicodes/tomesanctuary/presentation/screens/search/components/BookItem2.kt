package com.mungaicodes.tomesanctuary.presentation.screens.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mungaicodes.tomesanctuary.R
import com.mungaicodes.tomesanctuary.domain.model.Book

@Composable
fun BookItem2(
    book: Book,
) {

    Card(Modifier.size(140.dp, 220.dp)) {
        Box(Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(book.volumeInfo.imageLinks.smallThumbnail)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Scrim(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter)
            )
            Text(
                text = book.volumeInfo.title,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomStart),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun Scrim(modifier: Modifier) {
    Box(
        modifier = modifier.background(
            Brush.verticalGradient(
                listOf(Color.Transparent, Color(0x40000000))
            )
        )
    )
}
