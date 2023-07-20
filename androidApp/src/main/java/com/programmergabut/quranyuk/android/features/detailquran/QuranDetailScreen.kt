package com.programmergabut.quranyuk.android.features.detailquran

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.programmergabut.quranyuk.android.MyApplicationTheme
import com.programmergabut.quranyuk.android.R
import com.programmergabut.quranyuk.android.Screen
import com.programmergabut.quranyuk.android.features.alquran.components.SurahListItem
import com.programmergabut.quranyuk.android.features.detailquran.components.AyahListItem
import com.programmergabut.quranyuk.android.theme.AppColor

@Preview
@Composable
fun QuranDetailScreenPreview() {
    MyApplicationTheme {
        QuranDetailScreen(
            surahId = 1,
            numberOfAyahs = 285,
            navController = rememberNavController(),
            viewModel = FakeQuranDetailViewModel()
        )
    }
}

@Composable
fun QuranDetailScreen(
    surahId : Int,
    numberOfAyahs: Int,
    navController: NavController,
    viewModel: IQuranDetailViewModel
) {

    val allAyah = remember { viewModel.ayahById }

    LaunchedEffect(Unit) {
        viewModel.getAyahId(surahId)
    }

    Column(
        Modifier
            .fillMaxWidth()
            .background(AppColor.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 20.dp, top = 32.dp, end = 20.dp),
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
                        modifier = Modifier
                            .clickable {
                                navController.popBackStack()
                            },
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = "",
                    )
                }
                Column {
                    Text(
                        text = allAyah.value?.englishName ?: "",
                        fontFamily = FontFamily(Font(R.font.cairo_bold)),
                        fontSize = 14.sp,
                        color = AppColor.Primary,
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 2.dp),
                        text = (allAyah.value?.englishNameTranslation ?: "") + " (${numberOfAyahs} Verse)",
                        fontFamily = FontFamily(Font(R.font.cairo_regular)),
                        color = AppColor.White,
                        fontSize = 12.sp,
                    )
                }
            }
            Image(
                modifier = Modifier,
                painter = painterResource(R.drawable.ic_setting_white),
                contentDescription = "",
            )
        }

        if(allAyah.value?.ayah.isNullOrEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(color = AppColor.Primary)
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(bottom = 16.dp, start = 20.dp, end = 20.dp)
            ) {
                items(
                    items = allAyah.value?.ayah ?: emptyList(),
                    key = { it.number}
                ) { ayahs ->
                    AyahListItem(
                        data = ayahs,
                    )
                }
            }
        }
    }
}