package com.mungaicodes.tomesanctuary.presentation.screens.search.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mungaicodes.tomesanctuary.R
import com.mungaicodes.tomesanctuary.domain.model.Book
import com.mungaicodes.tomesanctuary.presentation.ui.theme.SelectedItem

@Composable
fun BookItem2(
    book: Book,
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column {
            Box(
                Modifier
                    .fillMaxSize()
                    .size(140.dp, 220.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(book.volumeInfo?.imageLinks?.thumbnail?.replace("http", "https"))
                        .crossfade(true)
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                Scrim(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .align(Alignment.BottomCenter)
                )
                book.volumeInfo?.let {
                    Row(modifier = Modifier.align(Alignment.BottomStart)) {
                        Text(
                            text = it.title,
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(0.75f),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        ItemExpandButton(
                            expanded = expanded,
                            onClick = { expanded = !expanded }
                        )
                    }
                }
            }
            if (expanded) {
                Card(
                    elevation = 4.dp,
                    border = BorderStroke(2.dp, SelectedItem),
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 4.dp),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(4.dp)
                    ) {
                        Text(text = book.volumeInfo?.maturityRating!!)
                    }
                }
            }
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
