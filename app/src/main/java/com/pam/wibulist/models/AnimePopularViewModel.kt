package com.pam.wibulist.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.wibulist.repositories.AnimePopularRepository
import com.pam.wibulist.repositories.AnimeUpcomingRepository
import kotlinx.coroutines.launch

class AnimePopularViewModel: ViewModel() {
    private var _animePopularList = mutableStateListOf<AnimePopularModel>()

    var errorMessage: String by mutableStateOf("")
    val animePopularList: List<AnimePopularModel>
        get() = _animePopularList

    fun getAnimePopularList() {
        viewModelScope.launch {
            val apiClient = AnimePopularRepository.getClient()
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