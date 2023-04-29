package com.pam.wibulist.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

open class ButtonNavItem (
    val title: String,
    val icon: ImageVector,
    val screen_route: String,
){
    object Home: ButtonNavItem("Home", Icons.Default.Home, screen_route = "Home")
    object Search: ButtonNavItem("Search", Icons.Default.Search, screen_route = "Search")
    object Trend: ButtonNavItem("Trend", Icons.Default.Star, screen_route = "Trend")
    object Profile: ButtonNavItem("Profile", Icons.Default.AccountCircle, screen_route = "Profile")
}