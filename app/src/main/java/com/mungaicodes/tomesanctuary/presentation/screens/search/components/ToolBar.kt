package com.mungaicodes.tomesanctuary.presentation.screens.search.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mungaicodes.tomesanctuary.presentation.screens.search.SearchScreenViewModel
import com.mungaicodes.tomesanctuary.presentation.ui.theme.TomeSanctuaryTheme

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
                Text(text = "Search...")
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