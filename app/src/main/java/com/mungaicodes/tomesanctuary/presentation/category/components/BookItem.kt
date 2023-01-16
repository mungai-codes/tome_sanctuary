package com.mungaicodes.tomesanctuary.presentation.category.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mungaicodes.tomesanctuary.R
import com.mungaicodes.tomesanctuary.domain.model.Book
import com.mungaicodes.tomesanctuary.presentation.ui.theme.LampLight
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme

@Composable
fun BookItem(
    book: Book,
    onClick: (String) -> Unit
) {

    Surface(
        modifier = Modifier
            .size(height = 200.dp, width = 150.dp)
            .clickable { onClick(book.id) },
        elevation = 16.dp,
        color = LampLight,
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(modifier = Modifier.weight(0.7f)) {
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
            }
            book.volumeInfo?.title?.let {
                Text(
                    text = it,
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Serif,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun BookItemPreview() {
    TomeSanctuaryTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}