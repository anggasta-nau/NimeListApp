package com.pam.wibulist.ui.Screens

import com.pam.wibulist.models.AnimeTrendModel
import com.pam.wibulist.models.AnimeTrendViewModel
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter


@Composable
fun MainScreenView(
    avm: AnimeTrendViewModel,
    navController: NavController
) {
    LaunchedEffect(
        Unit,
        block = {
            avm.getAnimeTrendList()
        }
    )
    Column {
        Text(
            text = "Top Anime",
            fontSize = 24.sp,
            fontWeight= FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(start = 20.dp, top = 10.dp)
        )

        when {
            avm.errorMessage.isEmpty() -> {
                AvmList(avl = avm.animeTrendList) { animeId, animeTitle, animeImgUrl, animeGenre, animeDeskripsi ->
                    Log.d("ClickItem", "this is anime id: $animeId")
                    navController.navigate("Detail?id=$animeId?title=$animeTitle?imgUrl=$animeImgUrl?genre=$animeGenre?Deskripsi=$animeDeskripsi")
                }
            }
            else -> Log.e("AVM", "Something happened")
        }
    }
}
@Composable
fun AvmList(avl: List<AnimeTrendModel>, itemClick: (index: Int, title: String, imgUrl: String, genre: String, Deskripsi:String)-> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        .padding(20.dp)
    ) {
        Text(
            text = "Trending Now",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(modifier = Modifier
            .fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(avl) {index, item ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(5.dp)
                    .shadow(elevation = 3.dp)
                    .clickable {
                        itemClick(item.id, item.title, item.imgUrl, item.genre, item.Deskripsi)
                    },
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = item.imgUrl,
                                builder = {
//                                    scale(Scale.FILL)
//                                    placeholder(R.drawable.notification_action_background)
//                                    transformations(CircleCropTransformation())
                                }
                            ),
                            contentDescription = item.Deskripsi
                            ,
                            modifier = Modifier
                                .fillMaxHeight()
//                                .weight(0.2f)
                        )
                        Column(modifier = Modifier
                            .padding(4.dp)
                            .fillMaxHeight(),
//                            .weight(0.8f),
                            verticalArrangement = Arrangement.Center
                        ) {
//                            Text(text = item.title)
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold
                            )
//                            Text(
//                                text = item.genre,
//                                style = MaterialTheme.typography.caption,
//                                modifier = Modifier
//                                    .padding(4.dp)
//                            )
//                            Text(
//                                text = item.Deskripsi,
//                                style = MaterialTheme.typography.body1,
//                                maxLines = 2,
//                                overflow = TextOverflow.Ellipsis
//                            )
                        }
                    }
                }
            }
        }
    }
}