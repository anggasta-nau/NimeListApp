package com.pam.wibulist.ui.Screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.pam.wibulist.models.*

@Composable
fun MainScreenView(
    avm: AnimeSearchViewModel,

    navController: NavController
) {
    var search by remember { mutableStateOf("") }
    LaunchedEffect(
        Unit,
        block = {
            avm.getAnimeSearchList()

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
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(29, 196, 206),
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color(29, 196, 206)
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .padding(start = 20.dp, top = 20.dp, end = 20.dp)
        )

        // Filtering the anime list based on search keyword
        val filteredList = avm.animeSearchList.filter {
            it.title.contains(search, ignoreCase = true)
        }

        // Show anime list only if there is no error message from view model
        if (avm.errorMessage.isEmpty()) {
            AvmAnimeList(avl = filteredList) { animeId, animeTitle, animeImgUrl, animeGenre, animeDeskripsi, animeRating, animeRelease ->
                Log.d("ClickItem", "this is anime id: $animeId")
                navController.navigate("Detail?id=$animeId?title=$animeTitle?imgUrl=$animeImgUrl?genre=$animeGenre?Deskripsi=$animeDeskripsi?rating=$animeRating?release=$animeRelease")
            }
        } else {
            Log.e("AVM", "Something happened")
        }
    }
}




@Composable
fun AvmAnimeList(
    avl: List<AnimeBannerModel>,
    itemClick: (index: Int, title: String, imgUrl: String, genre: String, Deskripsi: String, rating: String, release: String) -> Unit
) {
    val scrollState = rememberScrollState()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
    ) {
        itemsIndexed(avl) { index, item ->
            Box(
                modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp)
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
                        contentDescription = item.Deskripsi,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f/9f)
                    )
                    Column(modifier = Modifier.padding(4.dp)) {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.caption,
                        )

                        Text(
                            text = item.genre,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier
                                .background(
                                    Color.LightGray
                                )
                                .padding(4.dp)
                        )
                        Text(
                            text = item.Deskripsi,
                            style = MaterialTheme.typography.body1,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}


