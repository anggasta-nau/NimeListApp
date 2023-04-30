package com.pam.wibulist.ui.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pam.wibulist.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.pam.wibulist.ui.ButtonNavItem
import com.pam.wibulist.ui.theme.backgroundColor

@Composable
fun DetailScreen(
    id: String?,
    title: String?,
    imgUrl: String?,
    genre: String?,
    Deskripsi: String?,
    rating: String?,
    release: String?,

) {

    Column(
        modifier = Modifier
            .verticalScroll((rememberScrollState()))
            .background(MaterialTheme.colors.backgroundColor)
            .height(1000.dp)
    ) {
        Column( modifier = Modifier.padding(20.dp)) {
            IconButton(
                onClick = {

                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
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
                        .fillMaxWidth()
                        .height(400.dp)
                        .clip(RoundedCornerShape(10.dp))
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "$title",
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            Column() {
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Genre",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "$genre",
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Rating",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "$rating",
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Release",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "$release",
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Sypnosys",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "$Deskripsi",
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                    fontSize = 14.sp,
                )
            }
        }
    }


}