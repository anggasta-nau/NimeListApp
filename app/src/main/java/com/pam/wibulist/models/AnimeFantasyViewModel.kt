package com.pam.wibulist.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.wibulist.repositories.AnimeRepository
import kotlinx.coroutines.launch

class AnimeFantasyViewModel: ViewModel() {
    private var _animeFantasyList = mutableStateListOf<AnimeBannerModel>()

    var errorMessage: String by mutableStateOf("")
    val animeFantasyList: List<AnimeBannerModel>
        get() = _animeFantasyList

    fun getAnimeFantasyList() {
        viewModelScope.launch {
            val apiClient = AnimeRepository.getClient()
            try {
                _animeFantasyList.clear()
                _animeFantasyList.addAll(apiClient.getFantasyAnime())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}