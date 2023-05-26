package com.dicoding.kyosoappsubmission.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.kyosoappsubmission.R

@Composable
fun CustomAppBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    moveToFavoritePage: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    TopAppBar(
        modifier = modifier,
        backgroundColor = Color(0xFF87CEEB),
        elevation = 0.dp,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 5.dp),
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(R.drawable.app_logo),
                        contentDescription = "app logo",
                        modifier = Modifier
                            .size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(Color.White, RoundedCornerShape(50))
                    ) {
                        TextField(
                            value = query,
                            onValueChange = onQueryChange,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(horizontal = 8.dp)
                                .heightIn(min = 36.dp)
                                .onFocusChanged { focusState ->
                                    if (!focusState.isFocused) {
                                        focusManager.clearFocus()
                                    }
                                },
                            textStyle = TextStyle.Default,
                            singleLine = true,
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.search_hint),

                                ) },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                cursorColor = Color.Black,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "search",
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            trailingIcon = {
                                if (query.isNotEmpty()) {
                                    IconButton(onClick = {
                                        onQueryChange("")
                                        focusManager.clearFocus()
                                    }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = "clear",
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    IconButton(
                        onClick = moveToFavoritePage,

                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Favorite,
                            contentDescription = "about_page",
                            tint = Color.Red,
                            modifier = Modifier
                                .size(35.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    )
}




@Preview
@Composable
fun CustomAppBarPreview() {
    CustomAppBar(
        query = "Naruto",
        onQueryChange = {},
        modifier = Modifier.fillMaxWidth(),
        moveToFavoritePage = {}
    )
}
