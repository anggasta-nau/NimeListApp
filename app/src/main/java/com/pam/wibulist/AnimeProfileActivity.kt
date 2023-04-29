package com.pam.wibulist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.pam.wibulist.data.AnimeDetail
import com.pam.wibulist.ui.Screens.AnimeProfileScreen
import com.pam.wibulist.ui.theme.WibuListTheme

class AnimeProfileActivity : AppCompatActivity() {

    private val anime: AnimeDetail by lazy {
        intent?.getSerializableExtra(ANIME_ID) as AnimeDetail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WibuListTheme() {
                AnimeProfileScreen(anime = anime)
            }
        }
    }

    companion object {
        private const val ANIME_ID = "anime_id"
        fun newIntent(context: Context, anime: AnimeDetail) =
            Intent(context, AnimeProfileActivity::class.java).apply {
                putExtra(ANIME_ID, anime)
            }
    }
}