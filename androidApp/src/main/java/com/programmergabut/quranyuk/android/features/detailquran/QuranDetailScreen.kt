package com.programmergabut.quranyuk.android.features.detailquran

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.programmergabut.quranyuk.android.features.alquran.components.SwipeBackground
import com.programmergabut.quranyuk.android.features.common.component.Loading
import com.programmergabut.quranyuk.android.features.detailquran.components.AyahListItem
import com.programmergabut.quranyuk.android.theme.AppColor
import com.programmergabut.quranyuk.domain.model.ReadSurah
import kotlinx.coroutines.launch

@Preview
@Composable
fun QuranDetailScreenPreview() {
    MyApplicationTheme {
        QuranDetailScreen(
            surahId = 1,
            navController = rememberNavController(),
            viewModel = FakeQuranDetailViewModel()
        )
    }
}

@Composable
fun QuranDetailScreen(
    surahId: Int,
    navController: NavController,
    viewModel: IQuranDetailViewModel
) {

    val readSurah = remember { viewModel.readSurah }
    val insertLastRead by viewModel.insertLastRead
    val lastRead by  viewModel.lastRead
    var ayahId by remember { mutableIntStateOf(0) }
    var isLastReadSurah by remember { mutableStateOf(surahId == (lastRead?.surahId ?: 0)) }

    LaunchedEffect(Unit) {
        viewModel.getLastRead()
        viewModel.getAyahBySurahId(surahId)
    }

    LaunchedEffect(insertLastRead) {
        viewModel.getLastRead()
    }

    LaunchedEffect(lastRead) {
        ayahId = lastRead?.ayahId ?: 0
        isLastReadSurah = surahId == (lastRead?.surahId ?: 0)
        readSurah.value?.let { viewModel.updateAyahs(it) }
    }

    Column(
        Modifier
            .fillMaxWidth()
            .background(AppColor.Black)
    ) {
        ToolbarSurah(navController, readSurah.value)

        if(readSurah.value?.ayah.isNullOrEmpty()) {
            Loading()
        } else {
            ListAyah(readSurah.value, ayahId, isLastReadSurah) { ayahId ->
                viewModel.insertLastRead(ayahId, surahId, readSurah.value?.englishName ?: "")
            }
        }
    }
}

@Composable
fun ToolbarSurah(
    navController: NavController,
    allAyah: ReadSurah?
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
                    text = allAyah?.englishName ?: "",
                    fontFamily = FontFamily(Font(R.font.cairo_bold)),
                    fontSize = 14.sp,
                    color = AppColor.Primary,
                )
                Text(
                    modifier = Modifier
                        .padding(top = 2.dp),
                    text = (allAyah?.englishNameTranslation ?: "") + " (${allAyah?.ayah?.size ?: 0} Verse)",
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
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ListAyah(
    readSurah: ReadSurah?,
    ayahId: Int,
    isLastReadSurah: Boolean,
    callViewModel: (ayahId: Int) -> Unit
) {
    val context = LocalContext.current
    val lazyColumnListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier.padding(bottom = 16.dp),
        state = lazyColumnListState
    ) {
        coroutineScope.launch {
            val scrollTo = ayahId - 1
            if (scrollTo >= 0) {
                lazyColumnListState.scrollToItem(scrollTo)
            }
        }

        items(
            items = readSurah?.ayah ?: emptyList(),
            key = { it.number },
            itemContent = { ayahs ->
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                            callViewModel.invoke(ayahs.numberInSurah)
                            Toast.makeText(context, "Last read is ${readSurah?.number}:${ayahs.numberInSurah}", Toast.LENGTH_SHORT).show()
                            false
                        } else false
                    }
                )


                SwipeToDismiss(
                    state = dismissState,
                    modifier = Modifier.animateItemPlacement(),
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = {
                        FractionalThreshold(0f)
                    },
                    background = {
                        SwipeBackground(dismissState)
                    },
                    dismissContent = {
                        AyahListItem(
                            modifier = Modifier,
                            data = ayahs,
                            ayahId = ayahId,
                            isLastReadSurah = isLastReadSurah
                        )
                    }
                )
            }
        )
    }
}