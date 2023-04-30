package com.pam.wibulist.ui.Screens

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
import com.pam.wibulist.viewModel.sharedViewModel

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
        composable(ButtonNavItem.Profile.screen_route) {
            ProfileScreen(avm = vm2, navController = navController, sharedViewModel = sharedViewModel)
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
        backgroundColor = Color.White,
        contentColor = Color.Red
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
                selectedContentColor = Color.Blue.copy(0.7f),
                unselectedContentColor = Color.Blue.copy(0.4f),
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