package com.pam.wibulist.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.wibulist.repositories.AnimeRepository
import kotlinx.coroutines.launch

class AnimeTrendingViewModel: ViewModel() {
    private var _animeTrendingList = mutableStateListOf<AnimeBannerModel>()

    var errorMessage: String by mutableStateOf("")
    val animeTrendingList: List<AnimeBannerModel>
        get() = _animeTrendingList

    fun getAnimeTrendingList() {
        viewModelScope.launch {
            val apiClient = AnimeRepository.getClient()
            try {
                _animeTrendingList.clear()
                _animeTrendingList.addAll(apiClient.getTrendingAnime())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}