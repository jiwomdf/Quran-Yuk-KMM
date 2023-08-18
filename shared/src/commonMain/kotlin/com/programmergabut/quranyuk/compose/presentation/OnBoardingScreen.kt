package com.programmergabut.quranyuk.compose.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.programmergabut.quranyuk.compose.theme.BgDarkPurple
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun OnBoardingScreen() {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BgDarkPurple)
                .padding(top = 16.dp)
            ,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End,
        ) {
            Column{}
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(modifier = Modifier)
                Text(
                    modifier = Modifier,
                    text = "Let's Get Started!",
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp, start = 36.dp, end = 36.dp),
                    text = "Let's start your journey towards spiritual fulfillment with SholatYuk!",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }

            ButtonGetStarted()
        }
    }
}

@Composable
fun ButtonGetStarted() {
    Column(
        modifier = Modifier
            .padding(end = 24.dp, bottom = 40.dp),
    ) {
        Button(
            modifier = Modifier.size(width = 140.dp, height = 50.dp),
            onClick = {}
        ) {
            Text(
                text = "Get Started",
                color = Color.White
            )
        }
    }
}