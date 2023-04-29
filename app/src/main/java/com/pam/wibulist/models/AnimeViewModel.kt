package com.pam.wibulist.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.wibulist.repositories.AnimeRepository
import kotlinx.coroutines.launch

class AnimeViewModel: ViewModel() {

    private var _animeList = mutableStateListOf<AnimeModel>()

    var errorMessage: String by mutableStateOf("")
    val animeList: List<AnimeModel>
        get() = _animeList

    fun getAnimeList() {
        viewModelScope.launch {
            val apiClient = AnimeRepository.getClient()
            try {
                _animeList.clear()
                _animeList.addAll(apiClient.getAnime())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}