package com.pam.wibulist.ui.Screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.pam.wibulist.models.*
import com.pam.wibulist.ui.theme.backgroundColor
import com.pam.wibulist.ui.theme.buttonColor
import com.pam.wibulist.ui.theme.gener

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
            .background(MaterialTheme.colors.backgroundColor)
    ) {
        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            label = { Text(text = "Search", color = Color.White) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.buttonColor,
                unfocusedBorderColor = Color.LightGray,
                focusedLabelColor = MaterialTheme.colors.buttonColor,
                cursorColor = MaterialTheme.colors.buttonColor, // Menambahkan cursor color
                textColor = Color.White // Mengubah warna teks
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White // Mengubah warna ikon
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                .clip(RoundedCornerShape(20.dp)),
            textStyle = TextStyle(color = Color.White) // Menambahkan style untuk teks input
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


