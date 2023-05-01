package com.pam.wibulist.NavigationGraph

sealed class Screens(val route: String) {
    object LoginScreen : Screens(route = "Login_Screen")
    object SignUpScreen : Screens(route = "SignUp_Screen")
    object HomeScreen : Screens(route = "Home_Screen")
    object ChangePasswordScreen : Screens(route = "ChangePassword_Screen")
    object SearchScreen : Screens(route = "Search_Screen")
    object bottomNavGr : Screens(route = "Bottom_Nav")
    object genreAction : Screens(route = "genre_Action")
    object genreFantasy : Screens(route = "genre_Fantasy")
    object genreSlice : Screens(route = "genre_Slice")
    object genreComedy : Screens(route = "genre_Comedy")

}
