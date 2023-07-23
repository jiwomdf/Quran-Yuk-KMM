package com.programmergabut.quranyuk.android.features.alquran.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.programmergabut.quranyuk.android.R
import com.programmergabut.quranyuk.android.theme.AppColor

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun SwipeBackgroundPreview() {
    SwipeBackground(dismissState = rememberDismissState(
        DismissValue.DismissedToStart
    ))
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SwipeBackground(dismissState: DismissState) {
    val direction = dismissState.dismissDirection ?: return
    val color by animateColorAsState(
        when (dismissState.targetValue) {
            DismissValue.Default -> AppColor.Black
            DismissValue.DismissedToEnd -> Color.Green
            DismissValue.DismissedToStart -> AppColor.DarkPurple
        }
    )
    val alignment = when (direction) {
        DismissDirection.StartToEnd -> Alignment.CenterStart
        DismissDirection.EndToStart -> Alignment.CenterEnd
    }
    val scale by animateFloatAsState(
        if (dismissState.targetValue == DismissValue.Default) 0.5f else 1f
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(color)
            .padding(horizontal = 20.dp),
        contentAlignment = alignment
    ) {
        Column{
            Image(
                modifier = Modifier.scale(scale),
                painter = painterResource(R.drawable.ic_bookmark),
                contentDescription = "",
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "Mark",
                fontSize = 16.sp,
                color = AppColor.White,
                fontFamily = FontFamily(Font(R.font.cairo_bold)
                )
            )
        }
    }
}
