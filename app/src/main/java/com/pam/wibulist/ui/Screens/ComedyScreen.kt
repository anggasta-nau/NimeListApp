package com.pam.wibulist.ui.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.pam.wibulist.R
import com.pam.wibulist.models.AnimeBannerModel
import com.pam.wibulist.models.AnimeComedyViewModel
import com.pam.wibulist.models.AnimeFantasyViewModel
import com.pam.wibulist.models.AnimeFullModel
import com.pam.wibulist.ui.ButtonNavItem
import com.pam.wibulist.ui.theme.backgroundColor
import com.pam.wibulist.ui.theme.gener


@Composable
fun ComedyScreenView(
    avm: AnimeComedyViewModel,
    navController: NavController
) {
    LaunchedEffect(
        Unit,
        block = {
            avm.getAnimeComedyList()
        }
    )
    Column() {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.backgroundColor)
        ) {
            Row() {
                IconButton(
                    onClick = {
                        navController.navigate(route = ButtonNavItem.Home.screen_route)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                        contentDescription = null,
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp)
                    )
                }
                Text(
                    text = "Genre's Comedy",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier
                        .padding(start = 30.dp, top = 20.dp)
                )
            }

            when {
                avm.errorMessage.isEmpty() -> {
                    AvmComedyList(avl = avm.animeComedyList) { animeId, animeTitle, animeImgUrl, animeGenre, animeDeskripsi, animeRating, animeRelease ->
                        Log.d("ClickItem", "this is anime id: $animeId")
                        navController.navigate("Detail?id=$animeId?title=$animeTitle?imgUrl=$animeImgUrl?genre=$animeGenre?Deskripsi=$animeDeskripsi?rating=$animeRating?release=$animeRelease")
                    }
                }
                else -> Log.e("AVM", "Something happened")
            }
        }
    }
}
@Composable
fun AvmComedyList(avl: List<AnimeBannerModel>, itemClick: (index: Int, title: String, imgUrl: String, genre: String, Deskripsi:String, rating:String, release:String)-> Unit) {
    Box(modifier = Modifier
        .padding(20.dp)
        .fillMaxWidth()
        .height(1750.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        val scrollState = rememberScrollState()

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            itemsIndexed(avl) { index, item ->
                Box(
                    modifier = Modifier
                        .clickable {
                            itemClick(
                                item.id,
                                item.title,
                                item.imgUrl,
                                item.genre,
                                item.Deskripsi,
                                item.rating,
                                item.release,
                            )
                        }
                ) {
                    Column {
                        Image(
                            painter = rememberImagePainter(
                                data = item.imgBanner,
                                builder = {
                                    scale(Scale.FILL)
                                    placeholder(coil.compose.base.R.drawable.notification_action_background)
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .aspectRatio(16f / 9f)
                                .clip(RoundedCornerShape(10))
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(modifier = Modifier) {
                            Text(
                                text = item.title,
                                color = Color.White,
                                fontSize = 18.sp,
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(MaterialTheme.colors.gener),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = item.genre,
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .padding(5.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(MaterialTheme.colors.gener),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = item.Deskripsi,
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .padding(5.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(5.dp))

                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }

    }
}