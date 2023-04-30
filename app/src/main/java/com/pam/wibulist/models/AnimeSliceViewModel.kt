package com.pam.wibulist.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.wibulist.repositories.AnimeRepository
import kotlinx.coroutines.launch

class AnimeSliceViewModel: ViewModel() {
    private var _animeSliceList = mutableStateListOf<AnimeBannerModel>()

    var errorMessage: String by mutableStateOf("")
    val animeSliceList: List<AnimeBannerModel>
        get() = _animeSliceList

    fun getAnimeSliceList() {
        viewModelScope.launch {
            val apiClient = AnimeRepository.getClient()
            try {
                _animeSliceList.clear()
                _animeSliceList.addAll(apiClient.getSliceAnime())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}