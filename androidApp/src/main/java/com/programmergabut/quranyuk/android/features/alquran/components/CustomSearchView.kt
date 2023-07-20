package com.programmergabut.quranyuk.android.features.alquran.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.programmergabut.quranyuk.android.R
import com.programmergabut.quranyuk.android.theme.AppColor


@Preview
@Composable
fun Preview() {
    CustomSearchView(
        search = "",
        modifier = Modifier,
        onValueChange = {}
    )
}

@Composable
fun CustomSearchView(
    search: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
            .border(border = BorderStroke(1.dp, AppColor.Primary), shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 16.dp))
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = search,
            onValueChange = onValueChange,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = AppColor.Black,
                placeholderColor = AppColor.Gray,
                leadingIconColor = AppColor.Primary,
                trailingIconColor = AppColor.Primary,
                textColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = AppColor.Primary
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            },
            trailingIcon = {
                Image(
                    painter = painterResource(R.drawable.ic_search_filter),
                    contentDescription = "",
                )
            },
            placeholder = { Text(text = "Search") },
        )
    }
}