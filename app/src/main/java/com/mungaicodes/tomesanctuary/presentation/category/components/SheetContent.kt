package com.mungaicodes.tomesanctuary.presentation.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LibraryAdd
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.twotone.BookmarkAdd
import androidx.compose.material.icons.twotone.BookmarkAdded
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mungaicodes.tomesanctuary.R
import com.mungaicodes.tomesanctuary.domain.model.Book
import com.mungaicodes.tomesanctuary.presentation.ui.theme.GreenGrey50
import com.mungaicodes.tomesanctuary.presentation.ui.theme.LampLight
import com.mungaicodes.tomesanctuary.presentation.ui.theme.StatusBar

@Composable
fun SheetContent(
    book: Book?,
    onPreviewClick: (String) -> Unit,
    onSubscribe: () -> Unit,
    onSampleClick: (String) -> Unit,
    onSaveBookClicked: () -> Unit
) {


    var bookMarkSelected by remember {
        mutableStateOf(false)
    }

    val (bookMarkIcon, tint) = when (bookMarkSelected) {
        false -> Pair(Icons.TwoTone.BookmarkAdd, StatusBar)
        true -> Pair(Icons.TwoTone.BookmarkAdded, GreenGrey50.copy(alpha = 1f))
    }


    Box(
        modifier = Modifier
            .padding(top = 80.dp, start = 5.dp, end = 5.dp)
            .background(LampLight, RoundedCornerShape(8.dp))
    ) {
        Box(modifier = Modifier.wrapContentHeight()) {
            AsyncImage(
                modifier = Modifier
                    .width(120.dp)
                    .height(160.dp)
                    .align(alignment = Alignment.TopCenter)
                    .offset(y = (-75).dp, x = 0.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(
                        book?.volumeInfo?.imageLinks?.medium?.replace(
                            "http",
                            "https"
                        )
                    )
                    .crossfade(true)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
                    .build(),
                contentDescription = book?.volumeInfo?.imageLinks?.medium,
                contentScale = ContentScale.Crop,
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                    IconButton(onClick = {
                        bookMarkSelected = !bookMarkSelected
                        onSaveBookClicked()
                    }) {
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
                                book?.volumeInfo?.authors.toString().replace("[", "")
                                    .replace("]", "")
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
                            .heightIn(min = 50.dp, max = 225.dp)
                            .wrapContentHeight()
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
                            Text(
                                text = if (book?.volumeInfo?.description == null) "No description Available!"
                                else book.volumeInfo.description
                                    .replace("<p>", "")
                                    .replace("</p>", "")
                                    .replace("<ul>", "")
                                    .replace("</ul>", "")
                                    .replace("<li>", "")
                                    .replace("</li>", "")
                                    .replace("<i>", "")
                                    .replace("</i>", "")
                                    .replace("<b>", "")
                                    .replace("</b>", "")
                                    .replace("</wbr>", "")
                                    .replace("<br>", ""),
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Light,
                                textAlign = TextAlign.Start
                            )
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
                            .padding(vertical = 15.dp)
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
}
