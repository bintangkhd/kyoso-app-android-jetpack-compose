package com.dicoding.kyosoappsubmission.ui.screen.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicoding.kyosoappsubmission.R
import com.dicoding.kyosoappsubmission.ui.navigation.NavScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(controller: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF87CEEB)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Image(
            painter = painterResource(R.drawable.app_logo),
            contentDescription = "splash Screen",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)

        )
    }

    LaunchedEffect(key1 = true) {
        delay(1500)
        controller.navigate(NavScreen.MainPage.route)
    }
}