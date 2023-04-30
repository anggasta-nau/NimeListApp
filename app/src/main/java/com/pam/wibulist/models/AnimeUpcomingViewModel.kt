package com.pam.wibulist.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.wibulist.repositories.AnimeRepository
import com.pam.wibulist.repositories.AnimeUpcomingRepository
import kotlinx.coroutines.launch

class AnimeUpcomingViewModel: ViewModel() {
    private var _animeUpcomingList = mutableStateListOf<AnimeUpcomingModel>()

    var errorMessage: String by mutableStateOf("")
    val animeUpcomingList: List<AnimeUpcomingModel>
        get() = _animeUpcomingList

    fun getAnimeUpcomingList() {
        viewModelScope.launch {
            val apiClient = AnimeUpcomingRepository.getClient()
            try {
                _animeUpcomingList.clear()
                _animeUpcomingList.addAll(apiClient.getUpcomingAnime())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}