package com.pam.wibulist.ui.Screens

import android.util.Log
import com.pam.wibulist.viewModel.sharedViewModel
import com.pam.wibulist.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pam.wibulist.models.AnimeBannerModel
import com.pam.wibulist.models.AnimeBannerViewModel
import com.pam.wibulist.models.AnimeFullModel
import com.pam.wibulist.models.AnimeModel
import com.pam.wibulist.models.AnimePopularViewModel
import com.pam.wibulist.models.AnimeTrendViewModel
import com.pam.wibulist.models.AnimeUpcomingViewModel
import com.pam.wibulist.models.AnimeViewModel


@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    sharedViewModel: sharedViewModel,
    avm: AnimeViewModel,
    avm2: AnimeTrendViewModel,
    avm3: AnimeUpcomingViewModel,
    avm4: AnimePopularViewModel,
    avm5: AnimeBannerViewModel
){
    var name by rememberSaveable{ mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val setUserInformation = sharedViewModel.person
    val user = Firebase.auth.currentUser!!


//    name data
    sharedViewModel.retrieveData(
        email = setUserInformation!!.email,
        context = context
    ){ data ->
        name = data.name
    }
    LaunchedEffect(
        Unit,
        block = {
            avm.getAnimeList()
            avm2.getAnimeTrendList()
            avm3.getAnimeUpcomingList()
            avm4.getAnimePopularList()
            avm5.getAnimeBannerList()
        }
    )
    Column(
        modifier = Modifier
            .verticalScroll((rememberScrollState()))
            .height(1450.dp)) {
        Column{
            TopAppBar(
                backgroundColor = Color.White,
                contentColor = Color.Black,
            ){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "$name",
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        fontSize = 20.sp,
                    )
                }
            }
            when {
                avm5.animeBannerList.isNotEmpty() -> {
                    val randomIndex = (0 until avm5.animeBannerList.size).random()
                    val anime = avm5.animeBannerList[randomIndex]
                    AvmHero(anime) { animeId, animeTitle, animeImgUrl, animeGenre, animeDeskripsi, animeRating, animeRelease, animeBanner ->
                        Log.d("ClickItem", "this is anime id: $animeId")
                        navController.navigate("Detail?id=$animeId?title=$animeTitle?imgUrl=$animeImgUrl?genre=$animeGenre?Deskripsi=$animeDeskripsi?rating=$animeRating?release=$animeRelease")
                    }
                    Log.d("hero-item", "$anime")
                }
                else -> {
                    Text(text = "No data available")
                }
            }

            Column(
                modifier = Modifier
                    .padding(top = 20.dp)
            ) {
                LazyVerticalGrid(columns = GridCells.Adaptive(90.dp),
                    content = {
                        items(1) {
                            Box(
                                modifier = Modifier
                                    .size(width = 30.dp, height = 50.dp)
                                    .padding(start = 10.dp)
                            )
                            {
                                Image(
                                    painterResource(id = R.drawable.square), "title",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(30.dp)

                                )
                                Text(
                                    text = "Category", modifier = Modifier
                                        .padding(top = 30.dp)
                                        .padding(start = 20.dp), fontSize = 12.sp, color = Color.Gray
                                )
                            }
                        }
                        items(1) {
                            Box(
                                modifier = Modifier
                                    .size(width = 30.dp, height = 50.dp)
                                    .padding(start = 5.dp)
                            )
                            {
                                Image(
                                    painterResource(id = R.drawable.time), "title",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(30.dp)
                                )
                                Text(
                                    text = "Release", modifier = Modifier
                                        .padding(top = 30.dp)
                                        .padding(start = 30.dp), fontSize = 12.sp, color = Color.Gray
                                )
                            }
                        }
                        items(1) {
                            Box(
                                modifier = Modifier
                                    .size(width = 30.dp, height = 50.dp)
                                    .padding(start = 5.dp)
                            )
                            {
                                Image(
                                    painterResource(id = R.drawable.medal), "title",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(30.dp)
                                )
                                Text(
                                    text = "Top Hits", modifier = Modifier
                                        .padding(top = 30.dp)
                                        .padding(start = 27.dp), fontSize = 12.sp, color = Color.Gray
                                )
                            }
                        }
                        items(1) {
                            Box(
                                modifier = Modifier
                                    .size(width = 30.dp, height = 50.dp)
                                    .padding(start = 10.dp)
                            )
                            {
                                Image(
                                    painterResource(id = R.drawable.diamonds), "title",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(30.dp)
                                )
                                Text(
                                    text = "Premium", modifier = Modifier
                                        .padding(top = 30.dp)
                                        .padding(start = 27.dp), fontSize = 12.sp, color = Color.Gray
                                )
                            }
                        }
                    })
            }
            Column() {

                Text(
                    text = "For You",
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 10.dp),
                    fontWeight = FontWeight.W500,
                    color = Color.Black,
                    fontFamily = FontFamily.SansSerif,
                )
                when {
                    avm.errorMessage.isEmpty() -> {
                        AvmList(avl = avm.animeList) {animeId,animeTitle,animeImgUrl, animeGenre, animeDeskripsi ->
                            Log.d("ClickItem", "this is anime id: $animeId")
                            navController.navigate("Detail?id=$animeId?title=$animeTitle?imgUrl=$animeImgUrl?genre=$animeGenre?Deskripsi=$animeDeskripsi")
                        }
                    }
                    else -> Log.e("AVM", "Something happened")
                }

                Text(
                    text = "Trend",
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 10.dp),
                    fontWeight = FontWeight.W500,
                    color = Color.Black,
                    fontFamily = FontFamily.SansSerif,
                )
                when {
                    avm.errorMessage.isEmpty() -> {
                        AvmTrendsList(avl = avm2.animeTrendList) { animeId, animeTitle, animeImgUrl, animeGenre, animeDeskripsi ->
                            Log.d("ClickItem", "this is anime id: $animeId")
                            navController.navigate("Detail?id=$animeId?title=$animeTitle?imgUrl=$animeImgUrl?genre=$animeGenre?Deskripsi=$animeDeskripsi")
                        }
                    }
                    else -> Log.e("AVM", "Something happened")
                }

                Text(
                    text = "Popular",
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 10.dp),
                    fontWeight = FontWeight.W500,
                    color = Color.Black,
                    fontFamily = FontFamily.SansSerif,
                )
                when {
                    avm.errorMessage.isEmpty() -> {
                        AvmPopularList(avl = avm4.animePopularList) { animeId, animeTitle, animeImgUrl, animeGenre, animeDeskripsi, animeRating, animeRelease ->
                            Log.d("ClickItem", "this is anime id: $animeId")
                            navController.navigate("Detail?id=$animeId?title=$animeTitle?imgUrl=$animeImgUrl?genre=$animeGenre?Deskripsi=$animeDeskripsi?rating=$animeRating?release=$animeRelease")
                        }
                    }
                    else -> Log.e("AVM", "Something happened")
                }

                Text(
                    text = "Upcoming",
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 10.dp),
                    fontWeight = FontWeight.W500,
                    color = Color.Black,
                    fontFamily = FontFamily.SansSerif,
                )
                when {
                    avm.errorMessage.isEmpty() -> {
                        AvmUpcomingList(avl = avm3.animeUpcomingList) { animeId, animeTitle, animeImgUrl, animeGenre, animeDeskripsi, animeRating, animeRelease ->
                            Log.d("ClickItem", "this is anime id: $animeId")
                            navController.navigate("Detail?id=$animeId?title=$animeTitle?imgUrl=$animeImgUrl?genre=$animeGenre?Deskripsi=$animeDeskripsi?rating=$animeRating?release=$animeRelease")
                        }
                    }
                    else -> Log.e("AVM", "Something happened")
                }




            }
        }
    }

}

@Composable
fun AvmList(avl: List<AnimeModel>, itemClick: (index: Int, title: String, imgUrl: String, genre: String, Deskripsi:String)-> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)


    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(avl) { index, item ->
                Card(
                    modifier = Modifier
                        .width(250.dp)
                        .height(150.dp)
                        .padding(5.dp)
                        .clickable {
                            itemClick(item.id, item.title, item.imgUrl, item.genre, item.Deskripsi)
                        },
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(11, 175, 198).copy(alpha = 0.4f))
                            .padding(5.dp),

//                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = item.imgUrl,
                                builder = {
                                    scale(Scale.FILL)
//                                    placeholder(coil.base.R.drawable.notification_action_background)
//                                    transformations(CircleCropTransformation())
                                }
                            ),
                            contentDescription = item.Deskripsi,
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                        )

                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxHeight()
                                //.background(Color(7, 133, 151, 255).copy(alpha = 0.4f))
                                .weight(0.8f),
                            verticalArrangement = Arrangement.Center
                        ) {
//                            Text(text = item.title)
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(4.dp)

                            )
                            Text(
                                text = item.genre,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier
                                    .padding(4.dp)
                            )
                            Text(
                                text = item.Deskripsi,
                                style = MaterialTheme.typography.body1,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AvmTrendsList(avl: List<AnimeModel>, itemClick: (index: Int, title: String, imgUrl: String, genre: String, Deskripsi:String)-> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)


    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(avl) { index, item ->
                Card(
                    modifier = Modifier
                        .width(250.dp)
                        .height(150.dp)
                        .padding(5.dp)
                        .clickable {
                            itemClick(item.id, item.title, item.imgUrl, item.genre, item.Deskripsi)
                        },
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(11, 175, 198).copy(alpha = 0.4f))
                            .padding(5.dp),

//                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = item.imgUrl,
                                builder = {
                                    scale(Scale.FILL)
//                                    placeholder(coil.base.R.drawable.notification_action_background)
//                                    transformations(CircleCropTransformation())
                                }
                            ),
                            contentDescription = item.Deskripsi,
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                        )

                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxHeight()
                                //.background(Color(7, 133, 151, 255).copy(alpha = 0.4f))
                                .weight(0.8f),
                            verticalArrangement = Arrangement.Center
                        ) {
//                            Text(text = item.title)
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(4.dp)

                            )
                            Text(
                                text = item.genre,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier
                                    .padding(4.dp)
                            )
                            Text(
                                text = item.Deskripsi,
                                style = MaterialTheme.typography.body1,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

/// SESUAI DESIGN FIGMA MULAI INI
@Composable
fun AvmUpcomingList(avl: List<AnimeFullModel>, itemClick: (index: Int, title: String, imgUrl: String, genre: String, Deskripsi:String, rating:String, release:String)-> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)


    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(avl) { index, item ->
                Card(
                    modifier = Modifier
                        .width(250.dp)
                        .height(150.dp)
                        .padding(5.dp)
                        .clickable {
                            itemClick(item.id, item.title, item.imgUrl, item.genre, item.Deskripsi, item.rating, item.release)
                        },
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(11, 175, 198).copy(alpha = 0.4f))
                            .padding(5.dp),

//                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = item.imgUrl,
                                builder = {
                                    scale(Scale.FILL)
//                                    placeholder(coil.base.R.drawable.notification_action_background)
//                                    transformations(CircleCropTransformation())
                                }
                            ),
                            contentDescription = item.Deskripsi,
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                        )

                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxHeight()
                                //.background(Color(7, 133, 151, 255).copy(alpha = 0.4f))
                                .weight(0.8f),
                            verticalArrangement = Arrangement.Center
                        ) {
//                            Text(text = item.title)
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(4.dp)

                            )
                            Text(
                                text = item.genre,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier
                                    .padding(4.dp)
                            )
                            Text(
                                text = item.Deskripsi,
                                style = MaterialTheme.typography.body1,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AvmPopularList(avl: List<AnimeFullModel>, itemClick: (index: Int, title: String, imgUrl: String, genre: String, Deskripsi:String, rating:String, release:String)-> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)


    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(avl) { index, item ->
                Card(
                    modifier = Modifier
                        .width(250.dp)
                        .height(150.dp)
                        .padding(5.dp)
                        .clickable {
                            itemClick(item.id, item.title, item.imgUrl, item.genre, item.Deskripsi, item.rating, item.release)
                        },
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(11, 175, 198).copy(alpha = 0.4f))
                            .padding(5.dp),

//                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                data = item.imgUrl,
                                builder = {
                                    scale(Scale.FILL)
//                                    placeholder(coil.base.R.drawable.notification_action_background)
//                                    transformations(CircleCropTransformation())
                                }
                            ),
                            contentDescription = item.Deskripsi,
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                        )

                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxHeight()
                                //.background(Color(7, 133, 151, 255).copy(alpha = 0.4f))
                                .weight(0.8f),
                            verticalArrangement = Arrangement.Center
                        ) {
//                            Text(text = item.title)
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(4.dp)

                            )
                            Text(
                                text = item.genre,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier
                                    .padding(4.dp)
                            )
                            Text(
                                text = item.Deskripsi,
                                style = MaterialTheme.typography.body1,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AvmHero(anime: AnimeBannerModel, itemClick: (index: Int, title: String, imgUrl: String, genre: String, Deskripsi:String, rating:String, release:String, imgBanner:String)-> Unit){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {itemClick(anime.id, anime.title, anime.imgUrl, anime.genre, anime.Deskripsi, anime.rating, anime.release, anime.imgBanner) }
    ){
        Column {
            Image(
                painter = rememberImagePainter(
                    data = anime.imgBanner,
                    builder = {
                        scale(Scale.FILL)
                        placeholder(coil.compose.base.R.drawable.notification_action_background)
//                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = anime.Deskripsi,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f/9f)
            )
            Column(modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
            ) {
                Text(text = anime.title)
                Text(
                    text = anime.title,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = anime.genre,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .background(
                            Color.LightGray
                        )
                        .padding(4.dp)
                )
                Text(
                    text = anime.Deskripsi,
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


