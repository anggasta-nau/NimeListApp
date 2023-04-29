package com.pam.wibulist.ui.Screens

import com.pam.wibulist.viewModel.sharedViewModel
import com.pam.wibulist.R
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pam.wibulist.NavigationGraph.Screens
import com.pam.wibulist.models.AnimeModel
import com.pam.wibulist.models.AnimeTrendModel


@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    sharedViewModel: sharedViewModel,
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

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(4, 1, 18))){
        Column(modifier = Modifier
            .padding(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 15.dp))
            {
                Text(
                    text = "WibuList",
                    fontSize = 26.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text ="Hi $name",
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "How Are You?",
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
            )

            //Randomize Card
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                elevation = 10.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            //Trending Now
            Text(
                text ="Trending Now",
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Screens.bottomNavGr
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
fun AvmTrendsList(avl: List<AnimeTrendModel>, itemClick: (index: Int, title: String, imgUrl: String, genre: String, Deskripsi:String)-> Unit) {
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