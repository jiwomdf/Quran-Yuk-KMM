package com.programmergabut.quranyuk.android.features.alquran

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.programmergabut.quranyuk.android.theme.AppColor
import com.programmergabut.quranyuk.domain.model.Surah

@Composable
fun SurahListItem(
    data: Surah
) {
    Column(
        modifier = Modifier
            .background(AppColor.White)
    ) {
        Text(
            text = data.name,
            fontSize = 16.sp
        )
    }
}