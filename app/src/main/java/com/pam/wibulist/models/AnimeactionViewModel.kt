package com.pam.wibulist.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.wibulist.repositories.AnimeRepository
import kotlinx.coroutines.launch

class AnimeActionViewModel: ViewModel() {
    private var _animeActionList = mutableStateListOf<AnimeModel>()

    var errorMessage: String by mutableStateOf("")
    val animeActionList: List<AnimeModel>
        get() = _animeActionList

    fun getAnimeActionList() {
        viewModelScope.launch {
            val apiClient = AnimeRepository.getClient()
            try {
                _animeActionList.clear()
                _animeActionList.addAll(apiClient.getActionAnime())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}