package com.mungaicodes.tomesanctuary.presentation.screens.search.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mungaicodes.tomesanctuary.R
import com.mungaicodes.tomesanctuary.domain.model.Book
import com.mungaicodes.tomesanctuary.presentation.ui.theme.GreenGrey50
import com.mungaicodes.tomesanctuary.presentation.ui.theme.SelectedItem
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TextWhite

@Composable
fun BookItem2(
    book: Book,
    onClick: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clickable { onClick(book.id) }
    ) {
        Column {
            Box(
                Modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                    .fillMaxSize()
                    .size(140.dp, 220.dp)
                    .border(BorderStroke(2.dp, GreenGrey50))

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
                            color = TextWhite,
                            fontFamily = FontFamily.Cursive,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
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
                    elevation = 8.dp,
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
