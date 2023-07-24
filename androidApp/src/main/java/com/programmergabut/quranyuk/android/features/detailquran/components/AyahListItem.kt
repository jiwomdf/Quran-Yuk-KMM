package com.programmergabut.quranyuk.android.features.detailquran.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.programmergabut.quranyuk.android.R
import com.programmergabut.quranyuk.android.theme.AppColor
import com.programmergabut.quranyuk.domain.model.ReadSurah

@Preview
@Composable
fun Preview() {
    AyahListItem(
        modifier = Modifier,
        data = ReadSurah.Ayah(
            number = 1,
            text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
            textEn = "Dengan Mneybut nama Allah yang maha pengasih lagi maha penyang",
            numberInSurah = 1
        ),
        ayahId = 1 ,
        isLastReadSurah = true
    )
}

@Composable
fun AyahListItem(
    modifier: Modifier,
    data: ReadSurah.Ayah,
    ayahId: Int,
    isLastReadSurah: Boolean
) {
    val isAyahLastRead = data.numberInSurah == ayahId && isLastReadSurah

    Column(
        modifier = modifier
            .background(if (isAyahLastRead) AppColor.Dark2Purple else AppColor.Black)
            .padding(top = 8.dp, start = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier,
                        painter = painterResource(R.drawable.ic_number_surah),
                        contentDescription = "",
                    )
                    Text(
                        text = data.numberInSurah.toString(),
                        color = AppColor.White,
                        fontFamily = FontFamily(Font(R.font.cairo_bold)),
                        fontSize = 12.sp,
                    )
                }
            }
            Text(
                text = data.text,
                fontSize = 26.sp,
                color = AppColor.White,
                textAlign = TextAlign.End
            )
        }

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = data.textEn,
            fontSize = 14.sp,
            color = AppColor.White,
            fontFamily = FontFamily(Font(R.font.amiri_regular))
        )

        Divider(
            color = AppColor.Primary,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 8.dp)
        )

    }
}