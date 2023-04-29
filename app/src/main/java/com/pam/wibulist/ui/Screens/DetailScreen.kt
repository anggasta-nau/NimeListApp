package com.pam.wibulist.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.Scale

@Composable
fun DetailScreen(id: String?, title: String?,imgUrl: String?, genre: String?, Deskripsi: String? ) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberImagePainter(
                    data = "$imgUrl",
                    builder = {
                        scale(Scale.FILL)
                    }
                ),
                contentDescription = "$Deskripsi",
                modifier = Modifier
                    .height(500.dp)
                    .width(250.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "$title",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Genre /n "+"$genre",
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Rating /n" + "$genre",
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = Color.White
        )
        Text(
            text = "Descriptive /n "+"$Deskripsi",
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}