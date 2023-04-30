package com.pam.wibulist.NavigationGraph

import androidx.lifecycle.viewmodel.compose.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pam.wibulist.models.*
import com.pam.wibulist.ui.Screens.*
import com.pam.wibulist.viewModel.sharedViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
    vm1: AnimeViewModel = AnimeViewModel(),
    vm2: AnimeTrendViewModel = AnimeTrendViewModel(),
    vm3: AnimeActViewModel = AnimeActViewModel(),
    vm4: AnimeUpcomingViewModel = AnimeUpcomingViewModel(),
    vm5: AnimePopularViewModel = AnimePopularViewModel(),
    vm6: AnimeBannerViewModel = AnimeBannerViewModel(),
    vm7: AnimeFantasyViewModel = AnimeFantasyViewModel(),
    vm8: AnimeComedyViewModel = AnimeComedyViewModel(),
    vm9: AnimeSliceViewModel = AnimeSliceViewModel()
) {
    val sharedViewModel: sharedViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route
    ) {

        composable(route = Screens.HomeScreen.route) {
           HomeScreen(navController = navController, sharedViewModel = sharedViewModel, avm = vm1, avm2 = vm2, avm3 = vm4, avm4 = vm5, avm5 = vm6)
        }

        composable(route = Screens.bottomNavGr.route) {
            BottomNavigationMainScreenView(sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.ChangePasswordScreen.route) {
            ChangePasswordScreen( navController = navController, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.LoginScreen.route) {
            LoginScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen( navController = navController, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.genreAction.route) {
            ActionScreenView( navController = navController, avm = vm3)
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
    }

}
