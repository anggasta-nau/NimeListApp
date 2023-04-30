package com.pam.wibulist.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.wibulist.repositories.AnimeRepository
import kotlinx.coroutines.launch

class AnimeComedyViewModel: ViewModel() {

    private var _animeComedyList = mutableStateListOf<AnimeBannerModel>()

    var errorMessage: String by mutableStateOf("")
    val animeComedyList: List<AnimeBannerModel>
        get() = _animeComedyList

    fun getAnimeComedyList() {
        viewModelScope.launch {
            val apiClient = AnimeRepository.getClient()
            try {
                _animeComedyList.clear()
                _animeComedyList.addAll(apiClient.getComedyAnime())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }

}
