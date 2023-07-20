package com.programmergabut.quranyuk.android.features.alquran.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.programmergabut.quranyuk.android.R
import com.programmergabut.quranyuk.android.theme.AppColor
import com.programmergabut.quranyuk.domain.model.Surah

@Preview
@Composable
fun SurahListItemPreview() {
    SurahListItem(
        data = Surah(
            englishName = "Al-Fatihah",
            englishNameTranslation= "The Opener",
            name = "الفَاتِحَة",
            number = 0,
            numberOfAyahs = 1,
            revelationType = ""
        ),
        {}
    )
}

@Composable
fun SurahListItem(
    data: Surah,
    onItemClick: ((Surah) -> Unit)
) {
    Column(
        modifier = Modifier
            .background(AppColor.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
                .clickable { onItemClick(data) },
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
                        text = data.number.toString(),
                        color = AppColor.White,
                        fontFamily = FontFamily(Font(R.font.cairo_bold)),
                        fontSize = 12.sp,
                    )
                }
                Column {
                    Text(
                        text = data.englishName,
                        fontFamily = FontFamily(Font(R.font.cairo_bold)),
                        fontSize = 14.sp,
                        color = AppColor.Primary,
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 2.dp),
                        text = data.englishNameTranslation + " (${data.numberOfAyahs} Verse)",
                        fontFamily = FontFamily(Font(R.font.cairo_regular)),
                        color = AppColor.White,
                        fontSize = 12.sp,
                    )
                }
            }
            Text(
                text = data.name,
                fontSize = 20.sp,
                color = AppColor.White,
                fontFamily = FontFamily(Font(R.font.amiri_regular))
            )
        }

        Divider(
            color = AppColor.Primary,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )

    }
}