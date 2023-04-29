package com.pam.wibulist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.pam.wibulist.NavigationGraph.NavigationGraph
import com.pam.wibulist.ui.theme.WibuListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WibuListTheme {
                NavigationGraph()

            }
        }
    }
}