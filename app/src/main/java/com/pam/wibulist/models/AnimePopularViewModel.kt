package com.pam.wibulist.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.wibulist.repositories.AnimeRepository
import kotlinx.coroutines.launch

class AnimePopularViewModel: ViewModel() {
    private var _animePopularList = mutableStateListOf<AnimeFullModel>()

    var errorMessage: String by mutableStateOf("")
    val animePopularList: List<AnimeFullModel>
        get() = _animePopularList

    fun getAnimePopularList() {
        viewModelScope.launch {
            val apiClient = AnimeRepository.getClient()
            try {
                _animePopularList.clear()
                _animePopularList.addAll(apiClient.getPopularAnime())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}