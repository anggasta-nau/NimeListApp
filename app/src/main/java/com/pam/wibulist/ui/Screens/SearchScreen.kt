package com.pam.wibulist.ui.Screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.pam.wibulist.models.*

@Composable
fun MainScreenView(
    avm: AnimeViewModel,
    avm2: AnimeTrendViewModel,
    avm3: AnimeActionViewModel,
    navController: NavController
) {
    var search by remember { mutableStateOf("") }
    LaunchedEffect(
        Unit,
        block = {
            avm.getAnimeList()
            avm2.getAnimeTrendList()
            avm3.getAnimeActionList()
        }
    )
    Column(
        modifier = Modifier
            .verticalScroll((rememberScrollState()))
            .height(900.dp)
            .background(Color(4, 1, 18, 1))
    ) {
        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            label = {
                Text(
                    text = "Search",
                    color = Color.LightGray
                ) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(29, 196, 206),
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color(29, 196, 206)
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                ) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .padding(start = 20.dp, top = 20.dp, end = 20.dp)
        )

        Text(
            text = "Popular",
            Modifier.padding(20.dp),
            fontWeight = FontWeight.W500,
            color = Color.Black,
//            fontFamily = FontFamily.SansSerif,
        )
        when {
            avm.errorMessage.isEmpty() ->
            {
                AvmTrendList(avl = avm2.animeTrendList) { animeId, animeTitle, animeImgUrl, animeGenre, animeDeskripsi ->
                    Log.d("ClickItem", "this is anime id: $animeId")
                    navController.navigate("Detail?id=$animeId?title=$animeTitle?imgUrl=$animeImgUrl?genre=$animeGenre?Deskripsi=$animeDeskripsi")
                }
            }
            else -> Log.e("AVM", "Something happened")
        }
        Text(
            text = "Recommendation",
            Modifier.padding(20.dp),
            fontWeight = FontWeight.W500,
            color = Color.Black,
//            fontFamily = FontFamily.SansSerif,
        )
        when {
            avm.errorMessage.isEmpty() -> {
                AvmAnimeList(avl = avm.animeList){animeId,animeTitle,animeImgUrl, animeGenre, animeDeskripsi ->
                    Log.d("ClickItem", "this is anime id: $animeId")
                    navController.navigate("Detail?id=$animeId?title=$animeTitle?imgUrl=$animeImgUrl?genre=$animeGenre?Deskripsi=$animeDeskripsi")
                }
            }


            else -> Log.e("AVM", "Something happened")
        }
        Text(
            text = "Genres: Action",
            Modifier.padding(20.dp),
            fontWeight = FontWeight.W500,
            color = Color.Black,
//            fontFamily = FontFamily.SansSerif,
        )
        when {
            avm.errorMessage.isEmpty() ->
            {
                AvmActionList(avl = avm3.animeActionList){animeId,animeTitle,animeImgUrl, animeGenre, animeDeskripsi ->
                    Log.d("ClickItem", "this is anime id: $animeId")
                    navController.navigate("Detail?id=$animeId?title=$animeTitle?imgUrl=$animeImgUrl?genre=$animeGenre?Deskripsi=$animeDeskripsi")
                }
            }

            else -> Log.e("AVM", "Something happened")
        }

    }

}

@Composable
fun AvmAnimeList(
    avl: List<AnimeModel>,
    itemClick: (index: Int, title: String, imgUrl: String, genre: String, Deskripsi:String)-> Unit) {
    val scrollState = rememberScrollState()
    LazyRow(modifier = Modifier
        .fillMaxWidth(),
    ) {
        itemsIndexed(avl) {index, item ->
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp)
                    .height(175.dp)
                    .width(110.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(11, 175, 198).copy(alpha = 0.4f))
                    .clickable {
                        itemClick(item.id, item.title, item.imgUrl, item.genre, item.Deskripsi)
                    },
            ){
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(7, 133, 151, 255).copy(alpha = 0.4f))
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = item.imgUrl,

                            ),
                        contentDescription = item.Deskripsi,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(5.dp)),
//                                .weight(0.2f)
                    )

                }

                Text(
                    text = item.title,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .fillMaxWidth()
                )

                Text(
                    text = item.genre,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun AvmTrendList(avl: List<AnimeTrendModel>, itemClick: (index: Int, title: String, imgUrl: String, genre: String, Deskripsi:String)-> Unit) {
    LazyRow(modifier = Modifier
        .fillMaxWidth(),
    ) {
        itemsIndexed(avl) {index, item ->
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp)
                    .height(175.dp)
                    .width(110.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(11, 175, 198).copy(alpha = 0.4f))
                    .verticalScroll((rememberScrollState()))
                    .clickable {
                        itemClick(item.id, item.title, item.imgUrl, item.genre, item.Deskripsi)
                    },
            ){
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(7, 133, 151, 255).copy(alpha = 0.4f))
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = item.imgUrl,

                            ),
                        contentDescription = item.Deskripsi,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(5.dp)),
//                                .weight(0.2f)
                    )

                }

                Text(
                    text = item.title,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .fillMaxWidth()
                )

                Text(
                    text = item.genre,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun AvmActionList(avl: List<AnimeActionModel>,itemClick: (index: Int, title: String, imgUrl: String, genre: String, Deskripsi:String)-> Unit) {
    val scrollState = rememberScrollState()
    LazyRow(modifier = Modifier
        .fillMaxWidth()
    ) {
        itemsIndexed(avl) {index, item ->
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
                    .height(175.dp)
                    .width(110.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .verticalScroll((rememberScrollState()))
                    .background(Color(11, 175, 198).copy(alpha = 0.4f))
                    .clickable {
                        itemClick(item.id, item.title, item.imgUrl, item.genre, item.Deskripsi)
                    },
            ){
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(7, 133, 151, 255).copy(alpha = 0.4f))
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = item.imgUrl,

                            ),
                        contentDescription = item.Deskripsi,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(5.dp)),
//                                .weight(0.2f)
                    )

                }

                Text(
                    text = item.title,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .fillMaxWidth()
                )

                Text(
                    text = item.genre,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}



//@Composable
//@Preview
//fun SearchScreenPreview() {
//    SearchScreen()
//}