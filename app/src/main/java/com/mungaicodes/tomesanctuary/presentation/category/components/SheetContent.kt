package com.mungaicodes.tomesanctuary.presentation.category.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LibraryAdd
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.twotone.Bookmark
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungaicodes.tomesanctuary.domain.model.Book
import com.mungaicodes.tomesanctuary.presentation.ui.theme.GreenGrey50
import com.mungaicodes.tomesanctuary.presentation.ui.theme.LampLight
import com.mungaicodes.tomesanctuary.presentation.ui.theme.StatusBar

@Composable
fun SheetContent(
    book: Book?,
    onPreviewClick: (String) -> Unit,
    onSubscribe: () -> Unit,
    onSampleClick: (String) -> Unit
) {

    var bookMarkSelected by remember {
        mutableStateOf(false)
    }

    val (bookMarkIcon, tint) = when (bookMarkSelected) {
        false -> Pair(Icons.TwoTone.Bookmark, StatusBar)
        true -> Pair(Icons.TwoTone.Bookmark, GreenGrey50.copy(alpha = 1f))
    }

    Surface(
        color = LampLight,
        modifier = Modifier
            .height(650.dp)
            .fillMaxWidth()
            .padding(horizontal = 6.dp)
            .offset(y = 60.dp),
        elevation = 16.dp,
        shape = RoundedCornerShape(10.dp)
    ) {

        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Share,
                            contentDescription = "share",
                            tint = StatusBar
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Rounded.LibraryAdd,
                            contentDescription = null
                        )
                    }

                }
                IconButton(onClick = { bookMarkSelected = !bookMarkSelected }) {
                    Icon(
                        imageVector = bookMarkIcon,
                        contentDescription = "share",
                        tint = tint
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 22.dp)
            ) {

                Column(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = book?.volumeInfo?.categories.toString().replace("[", "")
                            .replace("]", ""),
                        color = GreenGrey50,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        text = "Author : ${
                            book?.volumeInfo?.authors.toString().replace("[", "").replace("]", "")
                        }",
                        color = GreenGrey50,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                    book?.volumeInfo?.title?.replace("[", "")?.let {
                        Text(
                            text = it.replace("]", ""),
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            fontFamily = FontFamily.Serif,
                        )
                    }
                    book?.volumeInfo?.subtitle?.replace("[", "")?.let {
                        Text(
                            text = it.replace("]", ""),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 15.dp)
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .height(225.dp)
                ) {

                    Text(
                        text = "DESCRIPTION",
                        fontWeight = FontWeight.SemiBold,
                        color = GreenGrey50,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .weight(weight = 1f, fill = false)
                    ) {
                        book?.volumeInfo?.description?.let {
                            Text(
                                text = it.replace("<p>", "")
                                    .replace("</p>", "")
                                    .replace("<ul>", "")
                                    .replace("</ul>", "")
                                    .replace("<li>", "")
                                    .replace("</li>", "")
                                    .replace("<i>", "")
                                    .replace("</i>", "")
                                    .replace("<b>", "")
                                    .replace("</b>", "")
                                    .replace("<br>", ""),
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Light,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    book?.volumeInfo?.let { theBook ->

                        BookExtra(
                            header = bookExtras[0],
                            detail = theBook.publisher,
                            modifier = Modifier.weight(1f)
                        )

                        BookExtra(
                            header = bookExtras[1],
                            detail = theBook.pageCount.toString(),
                            modifier = Modifier.weight(1f)
                        )

                        BookExtra(
                            header = bookExtras[2],
                            detail = if (theBook.ratingsCount == 0) "___" else theBook.ratingsCount.toString(),
                            modifier = Modifier.weight(1f)
                        )

                        BookExtra(
                            header = bookExtras[3],
                            detail = theBook.publishedDate,
                            modifier = Modifier.weight(1f)
                        )
                    }

                }

                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(30.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    book?.volumeInfo?.let { theBook ->

                        CustomOutlinedButton(
                            onClick = { onPreviewClick(theBook.previewLink) },
                            customOutlinedButton = customOutlinedButtons[0]
                        )

                        CustomOutlinedButton(
                            onClick = { onSubscribe() },
                            customOutlinedButton = customOutlinedButtons[1]
                        )

                        CustomOutlinedButton(
                            onClick = { onSampleClick(theBook.previewLink) },
                            customOutlinedButton = customOutlinedButtons[2]
                        )

                    }

                }
            }

        }

    }
}


//@Preview
//@Composable
//fun ContentPreview() {
//    TomeSanctuaryTheme {
//        SheetContent()
//    }
//}