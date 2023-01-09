package com.mungaicodes.tomesanctuary.presentation.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material.icons.twotone.Bookmark
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun SheetContent(book: Book?) {

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
                            fontSize = 18.sp,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            maxLines = 2
                        )
                    }
                    book?.volumeInfo?.subtitle?.replace("[", "")?.let {
                        Text(
                            text = it.replace("]", ""),
                            maxLines = 1,
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

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Publisher",
                                color = GreenGrey50,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = theBook.publisher,
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Pages",
                                color = GreenGrey50,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = theBook.pageCount.toString(),
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Rating",
                                color = GreenGrey50,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = theBook.ratingsCount.toString(),
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Published",
                                color = GreenGrey50,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = theBook.publishedDate,
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp
                            )
                        }
                    }

                }

                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(30.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                        border = BorderStroke(1.dp, Color.LightGray),
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.MenuBook,
                            contentDescription = "preview"
                        )
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                        Text(
                            text = "PREVIEW",
                            fontSize = 8.sp
                        )
                    }

                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                        border = BorderStroke(1.dp, Color.LightGray),
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.PlayCircleOutline,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                        Text(
                            text = "N/A",
                            fontSize = 8.sp
                        )
                    }

                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                        border = BorderStroke(1.dp, Color.LightGray)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.StickyNote2,
                            contentDescription = "sample"
                        )
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                        Text(
                            text = "SAMPLE",
                            fontSize = 8.sp
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