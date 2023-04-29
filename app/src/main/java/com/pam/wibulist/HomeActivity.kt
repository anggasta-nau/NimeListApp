package com.pam.wibulist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.pam.wibulist.ui.Screens.BottomNavigationMainScreenView
import com.pam.wibulist.ui.theme.WibuListTheme
import com.pam.wibulist.viewModel.sharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WibuListTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
//                    BottomNavigationMainScreenView(navController = NavController(), sharedViewModel = sharedViewModel() )
                }
            }
        }
    }
}