package com.mungaicodes.tomesanctuary.presentation.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungaicodes.tomesanctuary.presentation.ui.theme.GreenGrey50
import com.mungaicodes.tomesanctuary.presentation.ui.theme.StatusBar
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TextWhite

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    keyword: String,
    filter: String,
    scrollKeyWords: () -> Unit,
    scrollBookTypes: () -> Unit,
    onSearchClick: () -> Unit,
) {

    val focusManager = LocalFocusManager.current

    var isInFocus by remember {
        mutableStateOf(false)
    }

    BackHandler(enabled = isInFocus) {
        focusManager.clearFocus(true)
    }

    TextField(
        modifier = modifier
            .border(1.dp, StatusBar, RoundedCornerShape(10.dp))
            .onFocusChanged { focusState ->
                isInFocus = focusState.isFocused
            },
        value = value,
        onValueChange = { onValueChange(it) },
        textStyle = TextStyle(
            color = Color.DarkGray,
            fontFamily = FontFamily.Monospace,
            fontSize = 10.sp
        ),
        label = {
            Text(
                text = "Search...",
                color = GreenGrey50,
                fontFamily = FontFamily.Cursive
            )
        },
        placeholder = { Text(text = "Harry Potter", color = Color.LightGray) },
        maxLines = 1,
        singleLine = true,
        leadingIcon = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                TextButton(
                    onClick = { scrollBookTypes() },
                ) {
                    Text(
                        text = filter,
                        color = Color.DarkGray,
                        fontFamily = FontFamily.Cursive,
                        fontSize = 15.sp
                    )
                }
                TextButton(
                    onClick = { scrollKeyWords() }
                ) {
                    Text(
                        text = keyword,
                        color = Color.DarkGray,
                        fontFamily = FontFamily.Cursive,
                        fontSize = 15.sp
                    )
                }
            }
        },
        trailingIcon = {
            IconButton(onClick = {
                onSearchClick()
                focusManager.clearFocus(true)
            }) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "search button",
                    tint = GreenGrey50
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClick()
                focusManager.clearFocus(true)
            },
            onDone = {
                focusManager.clearFocus(true)
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.DarkGray,
            disabledTextColor = Color.Transparent,
            backgroundColor = TextWhite,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
    )
}