package com.mungaicodes.tomesanctuary.presentation.mylibrary.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mungaicodes.tomesanctuary.data.local.BookEntity
import com.mungaicodes.tomesanctuary.presentation.ui.theme.GreenGrey50
import com.mungaicodes.tomesanctuary.presentation.ui.theme.LampLight

@Composable
fun LibraryItemCard(
    modifier: Modifier = Modifier,
    book: BookEntity,
    elevation: State<Dp> = animateDpAsState(targetValue = 1.dp)
) {
    Surface(
        modifier = modifier
            .padding(horizontal = 10.dp),
        color = LampLight,
        elevation = elevation.value,
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 12.dp, top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(25.dp)
        ) {

            Surface(
                modifier = modifier,
                shape = RoundedCornerShape(15.dp),
            ) {
                Box(
                    modifier = Modifier
                        .height(120.dp)
                        .width(85.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(book.mediumThumbnail?.replace("http", "https"))
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Column(
                modifier = Modifier
                    .align(Alignment.Top)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = book.authors.toString().replace("[", "")
                        .replace("]", ""),
                    color = GreenGrey50,
                    fontFamily = FontFamily.Serif,
                    fontSize = 15.sp
                )
                book.title?.let {
                    Text(
                        text = it,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }

    }
}


//@Preview(showBackground = true)
//@Composable
//fun LibraryItemCardPreview() {
//    TomeSanctuaryTheme {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            LibraryItemCard(item = libraryItems[0])
//        }
//    }
//}