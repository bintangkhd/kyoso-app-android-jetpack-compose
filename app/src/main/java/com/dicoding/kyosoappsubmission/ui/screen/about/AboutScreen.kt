package com.dicoding.kyosoappsubmission.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.kyosoappsubmission.R


@Composable
fun ProfileScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.about_image),
                contentDescription = "About Photo",
                modifier = Modifier
                .size(150.dp)
                .clip(
                    CircleShape
                ))

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "M. Bintang Khadafi",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "bintangkhadafi2772@gmail.com",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}