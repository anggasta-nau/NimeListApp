package com.pam.wibulist.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.wibulist.repositories.AnimeRepository
import kotlinx.coroutines.launch

class AnimeSearchViewModel: ViewModel() {
    private var _animeSearchList = mutableStateListOf<AnimeBannerModel>()

    var errorMessage: String by mutableStateOf("")
    val animeSearchList: List<AnimeBannerModel>
        get() = _animeSearchList

    fun getAnimeSearchList() {
        viewModelScope.launch {
            val apiClient = AnimeRepository.getClient()
            try {
                _animeSearchList.clear()
                _animeSearchList.addAll(apiClient.getSearchAnime())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}