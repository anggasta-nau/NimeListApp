package com.pam.wibulist.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.wibulist.repositories.AnimeRepository
import kotlinx.coroutines.launch

class AnimeActViewModel: ViewModel() {
    private var _animeActList = mutableStateListOf<AnimeBannerModel>()

    var errorMessage: String by mutableStateOf("")
    val animeActList: List<AnimeBannerModel>
        get() = _animeActList

    fun getAnimeActList() {
        viewModelScope.launch {
            val apiClient = AnimeRepository.getClient()
            try {
                _animeActList.clear()
                _animeActList.addAll(apiClient.getActAnime())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}