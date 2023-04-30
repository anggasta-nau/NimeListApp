package com.pam.wibulist.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.wibulist.repositories.AnimeRepository
import kotlinx.coroutines.launch

class AnimeBannerViewModel: ViewModel() {
    private var _animeBannerList = mutableStateListOf<AnimeBannerModel>()

    var errorMessage: String by mutableStateOf("")
    val animeBannerList: List<AnimeBannerModel>
        get() = _animeBannerList

    fun getAnimeBannerList() {
        viewModelScope.launch {
            val apiClient = AnimeRepository.getClient()
            try {
                _animeBannerList.clear()
                _animeBannerList.addAll(apiClient.getBannerAnime())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}