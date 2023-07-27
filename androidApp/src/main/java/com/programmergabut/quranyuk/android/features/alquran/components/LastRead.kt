package com.programmergabut.quranyuk.android.features.alquran.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.programmergabut.quranyuk.android.R
import com.programmergabut.quranyuk.android.theme.AppColor
import com.programmergabut.quranyuk.domain.model.LastRead

@Preview
@Composable
fun LastReadPreview() {
    Column {
        LastRead(
            modifier = Modifier,
            LastRead(
                surahName = "Al-Ma'idah",
                surahId = 5,
                ayahId = 98
            )
        ){}
        LastRead(
            modifier = Modifier.padding(top = 20.dp),
            null
        ){}
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LastRead(
    modifier: Modifier,
    lastRead: LastRead?,
    onClick: (lastRead: LastRead?) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8),
        backgroundColor = AppColor.DarkPurple,
        onClick =  {
            onClick.invoke(lastRead)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, top = 12.dp, bottom = 12.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = lastRead?.surahName ?: "You’ve not read yet",
                    color = AppColor.White,
                    fontSize = 14.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = if(lastRead != null){
                        "${lastRead.surahId}:${lastRead.ayahId}"
                    } else {
                        "Swipe left on ayah to save last read"
                    },
                    color = AppColor.White,
                    fontSize = 12.sp
                )
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = "Last Read",
                    color = AppColor.White,
                    fontSize = 12.sp
                )
            }
            Image(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .width(40.dp)
                    .height(40.dp),
                painter = painterResource(id = R.drawable.ic_book_24),
                contentDescription = "",
            )
        }

    }
}