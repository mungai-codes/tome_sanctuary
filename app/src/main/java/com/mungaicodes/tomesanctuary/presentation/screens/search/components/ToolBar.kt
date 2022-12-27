package com.mungaicodes.tomesanctuary.presentation.screens.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mungaicodes.tomesanctuary.presentation.screens.search.SearchScreenViewModel
import com.mungaicodes.tomesanctuary.presentation.ui.theme.SelectedItem
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme
import com.mungaicodes.tomesanctuary.util.Constants
import java.util.*

@Composable
fun Toolbar(
    viewModel: SearchScreenViewModel,
) {

    val state = viewModel.uiState.collectAsState().value
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {
        TextField(
            value = state.searchQuery,
            onValueChange = viewModel::onQueryChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = {
                Text(
                    text = "Search...",
                    fontFamily = FontFamily.Cursive,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.onSearch()
                    focusManager.clearFocus(true)
                },
            ),
            leadingIcon = {
                Box(contentAlignment = Alignment.Center) {
                    IconButton(onClick = {
                        viewModel.expandDropDown()
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.MoreVert,
                            contentDescription = "Select search keyword"
                        )
                    }
                    //drop down menu
                    DropdownMenu(
                        expanded = state.menuExpanded,
                        onDismissRequest = {
                            viewModel.closeDropdown()
                        },
                        modifier = Modifier
                            .border(2.dp, Color.LightGray, RoundedCornerShape(2.dp))
                            .background(
                                TextFieldDefaults
                                    .textFieldColors()
                                    .backgroundColor(enabled = true).value
                            )
                    ) {
                        Text(
                            text = state.searchKeyWord.uppercase(Locale.ROOT),
                            modifier = Modifier.padding(14.dp),
                            color = SelectedItem,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )
                        Divider()
                        Constants.SEARCH_KEYwORD.forEach { value ->
                            if (value != state.searchKeyWord) {
                                DropdownMenuItem(
                                    onClick = {
                                        viewModel.keywordSelector(value)
                                        viewModel.closeDropdown()
                                    },
                                ) {
                                    Text(
                                        text = value.uppercase(Locale.ROOT).replace(":", ""),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Light,
                                        fontFamily = FontFamily.Monospace
                                    )
                                }
                            }
                        }
                    }
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.onSearch()
                    focusManager.clearFocus(true)
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "search button"
                    )
                }
            },
            textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ToolBarPreview() {
    TomeSanctuaryTheme {
        val viewModel: SearchScreenViewModel = hiltViewModel()
        Toolbar(viewModel)
    }
}