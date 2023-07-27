package com.programmergabut.quranyuk.android.features.alquran

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
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
import com.programmergabut.quranyuk.android.features.alquran.components.CustomSearchView
import com.programmergabut.quranyuk.android.features.alquran.components.LastRead
import com.programmergabut.quranyuk.android.features.alquran.components.SurahListItem
import com.programmergabut.quranyuk.android.features.common.component.Loading
import com.programmergabut.quranyuk.android.theme.AppColor
import com.programmergabut.quranyuk.domain.model.Surah

@Preview
@Composable
fun QuranPreview() {
    MyApplicationTheme {
        QuranScreen(
            rememberNavController(),
            FakeQuranViewModel()
        )
    }
}

@Composable
fun QuranScreen(
    navController: NavController,
    viewModel: IQuranViewModel
) {

    val allSurah = remember { mutableStateListOf<Surah>() }
    val tempSurah by  viewModel.tempSearch
    val lastRead by remember { viewModel.lastRead }
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getAllSurah()
        viewModel.getLastRead()
    }

    LaunchedEffect(tempSurah) {
        allSurah.clear()
        allSurah.addAll(tempSurah.toMutableStateList())
    }

    Column(
        Modifier
            .fillMaxWidth()
            .background(AppColor.Black)
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp, top = 32.dp, bottom = 16.dp),
            text = "Al-Qur’an",
            fontSize = 28.sp,
            color = AppColor.Primary,
            fontFamily = FontFamily(Font(R.font.cairo_bold)),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CustomSearchView(search = searchText, onValueChange = {
                searchText = it
                viewModel.searchSurah(it)
            })
        }

        LastRead(
            modifier = Modifier.padding(start = 18.dp, end = 18.dp),
            lastRead = lastRead,
            onClick = {
                navController.navigate(Screen.QuranDetailScreen.route + "/${it?.surahId ?: ""}")
            }
        )

        if(allSurah.isEmpty()) {
            Loading()
        } else {
            LazyColumn(
                modifier = Modifier.padding(bottom = 16.dp, start = 20.dp, end = 20.dp, top = 16.dp)
            ) {
                items(allSurah){ surahs ->
                    SurahListItem(
                        data = surahs,
                        onItemClick = {
                            navController.navigate(Screen.QuranDetailScreen.route + "/${surahs.number}")
                        }
                    )
                }
            }
        }

    }
}
