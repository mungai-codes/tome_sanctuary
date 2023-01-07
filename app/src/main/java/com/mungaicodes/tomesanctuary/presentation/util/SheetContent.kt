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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungaicodes.tomesanctuary.R
import com.mungaicodes.tomesanctuary.presentation.ui.theme.GreenGrey50
import com.mungaicodes.tomesanctuary.presentation.ui.theme.LampLight
import com.mungaicodes.tomesanctuary.presentation.ui.theme.StatusBar
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme

@Composable
fun SheetContent() {

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
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp)
            .offset(y = 80.dp),
        elevation = 16.dp,
        shape = RoundedCornerShape(10.dp)
    ) {

        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp)
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
                        text = "Android",
                        color = GreenGrey50,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "Author : Mungai",
                        color = GreenGrey50,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "Hollywood Stalwarts in Literature",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text =
                        "A brief overview of how good of an android developer I am.",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 15.dp)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .height(200.dp)
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
                            text = stringResource(id = R.string.test_paragraph),
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Publisher",
                            color = GreenGrey50,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Pages",
                            textAlign = TextAlign.Center
                        )
                    }
                    Column {
                        Text(
                            text = "Pages",
                            color = GreenGrey50,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Rating",
                            textAlign = TextAlign.Center
                        )
                    }
                    Column {
                        Text(
                            text = "Rating",
                            color = GreenGrey50,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Published",
                            textAlign = TextAlign.Center
                        )
                    }
                    Column {
                        Text(
                            text = "Published",
                            color = GreenGrey50,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Publisher",
                            textAlign = TextAlign.Center
                        )
                    }

                }

                Row(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
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


@Preview
@Composable
fun ContentPreview() {
    TomeSanctuaryTheme {
        SheetContent()
    }
}