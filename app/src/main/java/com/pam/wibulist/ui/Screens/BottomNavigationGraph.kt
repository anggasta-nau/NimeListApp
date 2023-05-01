package com.pam.wibulist.ui.Screens

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.pam.wibulist.NavigationGraph.Screens
import com.pam.wibulist.R
import com.pam.wibulist.models.AnimeActViewModel
import com.pam.wibulist.models.AnimeActionViewModel
import com.pam.wibulist.models.AnimeBannerViewModel
import com.pam.wibulist.models.AnimeComedyViewModel
import com.pam.wibulist.models.AnimeFantasyViewModel
import com.pam.wibulist.models.AnimePopularViewModel
import com.pam.wibulist.models.AnimeSearchViewModel
import com.pam.wibulist.models.AnimeSliceViewModel
import com.pam.wibulist.models.AnimeTrendViewModel
import com.pam.wibulist.models.AnimeTrendingViewModel
import com.pam.wibulist.models.AnimeUpcomingViewModel
import com.pam.wibulist.models.AnimeViewModel
import com.pam.wibulist.ui.ButtonNavItem
import com.pam.wibulist.ui.theme.backgroundColor
import com.pam.wibulist.ui.theme.buttonColor
import com.pam.wibulist.viewModel.sharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun NavigationGraph(
    sharedViewModel: sharedViewModel,
    navController: NavHostController,
    vm1: AnimeViewModel = AnimeViewModel(),
    vm2: AnimeTrendViewModel = AnimeTrendViewModel(),
    vm3: AnimeActionViewModel = AnimeActionViewModel(),
    vm4: AnimeUpcomingViewModel = AnimeUpcomingViewModel(),
    vm5: AnimePopularViewModel = AnimePopularViewModel(),
    vm6: AnimeTrendingViewModel = AnimeTrendingViewModel(),
    vm7: AnimeFantasyViewModel = AnimeFantasyViewModel(),
    vm8: AnimeComedyViewModel = AnimeComedyViewModel(),
    vm9: AnimeSliceViewModel = AnimeSliceViewModel(),
    vm10: AnimeActViewModel = AnimeActViewModel(),
    vm11: AnimeBannerViewModel = AnimeBannerViewModel(),
    vm12: AnimeSearchViewModel = AnimeSearchViewModel()
) {
    val lContext = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = ButtonNavItem.Home.screen_route
    ) {
        composable(ButtonNavItem.Home.screen_route) {
                  HomeScreen(sharedViewModel = sharedViewModel, navController = navController, avm = vm1, avm2 = vm2, avm3 = vm4, avm4 = vm5, avm5 = vm11)
        //            DefaultPreview()
//            LandingPage(avm = vm1)
//            MainScreenView(avm = vm1, avm2 = vm2, navController = navController)
        }
        composable(ButtonNavItem.Search.screen_route) {
            MainScreenView(avm = vm12, navController = navController)
//            SearchScreen()
        }
        composable(ButtonNavItem.Trend.screen_route) {
            MainScreenView(avm = vm6, navController = navController)
//            TrendScreenPreview() {
//                    lContext.startActivity(AnimeProfileActivity.newIntent(lContext, it))
//            }
        }
        composable(route = Screens.genreAction.route) {
            ActionScreenView( navController = navController, avm = vm10)
        }
        composable(route = Screens.genreFantasy.route) {
            FantasyScreenView( navController = navController, avm = vm7)
        }
        composable(route = Screens.genreComedy.route) {
            ComedyScreenView( navController = navController, avm = vm8)
        }
        composable(route = Screens.genreSlice.route) {
            SliceofLifeScreenView( navController = navController, avm = vm9)
        }
        composable(ButtonNavItem.Profile.screen_route) {
            ProfileScreen(avm = vm2, navController = navController, sharedViewModel = sharedViewModel, onSubmitActionEvent = ::uploadImage)
        }
        composable(
            route = "Detail" + "?id={id}?title={title}?imgUrl={imgUrl}?genre={genre}?Deskripsi={Deskripsi}?rating={rating}?release={release}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue = "Anime"
                    nullable = true
                },
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "Anime"
                    nullable = true
                }
                ,
                navArgument("imgUrl") {
                    type = NavType.StringType
                    defaultValue = "Anime"
                    nullable = true
                },
                navArgument("imgBanner") {
                    type = NavType.StringType
                    defaultValue = "Anime"
                    nullable = true
                },
                navArgument("genre") {
                    type = NavType.StringType
                    defaultValue = "Anime"
                    nullable = true
                },
                navArgument("Deskripsi") {
                    type = NavType.StringType
                    defaultValue = "Anime"
                    nullable = true
                },
                navArgument("rating") {
                    type = NavType.StringType
                    defaultValue = "Anime"
                    nullable = true
                },
                navArgument("release") {
                    type = NavType.StringType
                    defaultValue = "Anime"
                    nullable = true
                }
            )
        ) { navBackStackEntry: NavBackStackEntry ->
            DetailScreen(
                id=navBackStackEntry.arguments?.getString("id") ,
                title = navBackStackEntry.arguments?.getString("title") ,
                imgUrl = navBackStackEntry.arguments?.getString("imgUrl") ,
                genre = navBackStackEntry.arguments?.getString("genre") ,
                Deskripsi = navBackStackEntry.arguments?.getString("Deskripsi"),
                rating = navBackStackEntry.arguments?.getString("rating"),
                release = navBackStackEntry.arguments?.getString("release")


            )
        }
        composable(
            route = "Detail" + "?id={id}?title={title}?imgUrl={imgUrl}?genre={genre}?Deskripsi={Deskripsi}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.StringType
                    defaultValue = "Anime"
                    nullable = true
                },
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "Anime"
                    nullable = true
                }
                ,
                navArgument("imgUrl") {
                    type = NavType.StringType
                    defaultValue = "Anime"
                    nullable = true
                },
                navArgument("genre") {
                    type = NavType.StringType
                    defaultValue = "Anime"
                    nullable = true
                },
                navArgument("Deskripsi") {
                    type = NavType.StringType
                    defaultValue = "Anime"
                    nullable = true
                }
            )
        ) { navBackStackEntry: NavBackStackEntry ->
            DetailScreen2(
                id=navBackStackEntry.arguments?.getString("id") ,
                title = navBackStackEntry.arguments?.getString("title") ,
                imgUrl = navBackStackEntry.arguments?.getString("imgUrl") ,
                genre = navBackStackEntry.arguments?.getString("genre") ,
                Deskripsi = navBackStackEntry.arguments?.getString("Deskripsi")
//                rating = navBackStackEntry.arguments?.getString("rating")

            )
        }
    }
}

@Composable
fun BottomNavigation(
    navController: NavController
){
    val items = listOf(
        ButtonNavItem.Home,
        ButtonNavItem.Search,
        ButtonNavItem.Trend,
        ButtonNavItem.Profile
    )
    androidx.compose.material.BottomNavigation(
        //backgroundColor = colorResource(id = R.color.teal_200),
        backgroundColor = MaterialTheme.colors.backgroundColor,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(
                    imageVector = item.icon,
                    contentDescription = "${item.title} icon")
                },
                label = {
                    Text(text = item.title,
                        fontSize = 9.sp)
                },
                selectedContentColor = MaterialTheme.colors.buttonColor,
                unselectedContentColor = Color.White,
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavigationMainScreenView(sharedViewModel: sharedViewModel){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(
                navController = navController,
            )
        }
    ) {
        NavigationGraph(navController = navController, sharedViewModel = sharedViewModel)
    }
}

fun uploadImage(img: ImageBitmap, context: Context, emailpic: String)
{
    val fStorage = Firebase.storage
    val storageRef = fStorage.reference

    // Set file name to timestamp
    val fileName = SimpleDateFormat("yyyyMMddHHmm'.png'").format(Date())
    val ref = storageRef.child("images/$fileName")

    // convert ImageBitmap to ByteArray
    val stream = ByteArrayOutputStream()
    img.asAndroidBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream)
    val image = stream.toByteArray()

    // Upload process
    var uploadTask = ref.putBytes(image)

    val urlTask = uploadTask.continueWithTask { task ->
        if (!task.isSuccessful) {
            task.exception?.let {
                throw it
            }
        }
        ref.downloadUrl
    }.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val downloadUri = task.result
            addData(downloadUri.toString(), context = context, emailpic)
        } else {
            Toast.makeText(context, context.getString(R.string.failed_to_add_data), Toast.LENGTH_SHORT).show()
            // Handle failures
            // ...failures
        }
    }
}


fun addData(imgUrl: String, context: Context, emailpic: String) = CoroutineScope(Dispatchers.IO).launch {

    val fFirestore = Firebase.firestore

    // Add a new document with a generated id.
    val data = hashMapOf(
        "EmailProv" to emailpic,
        "imgUrl" to imgUrl
    )

    fFirestore.collection("images")
        .add(data)
        .addOnSuccessListener { documentReference ->
            Toast.makeText(context, context.getString(R.string.profile_saved), Toast.LENGTH_SHORT).show()
        }
        .addOnFailureListener { e ->
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
}
