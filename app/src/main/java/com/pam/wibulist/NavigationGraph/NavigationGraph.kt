package com.pam.wibulist.NavigationGraph

import androidx.lifecycle.viewmodel.compose.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pam.wibulist.ui.Screens.*
import com.pam.wibulist.viewModel.sharedViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    val sharedViewModel: sharedViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route
    ) {

        composable(route = Screens.HomeScreen.route) {
           HomeScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(route = Screens.bottomNavGr.route) {
            BottomNavigationMainScreenView(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(route = Screens.ChangePasswordScreen.route) {
            ChangePasswordScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.LoginScreen.route) {
            LoginScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen( navController = navController, sharedViewModel = sharedViewModel)
        }
    }

}