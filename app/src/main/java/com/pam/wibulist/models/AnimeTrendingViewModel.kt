package com.pam.wibulist.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.wibulist.repositories.AnimePopularRepository
import com.pam.wibulist.repositories.AnimeTrendingRepository
import kotlinx.coroutines.launch

class AnimeTrendingViewModel: ViewModel() {
    private var _animeTrendingList = mutableStateListOf<AnimeTrendingModel>()

    var errorMessage: String by mutableStateOf("")
    val animeTrendingList: List<AnimeTrendingModel>
        get() = _animeTrendingList

    fun getAnimeTrendingList() {
        viewModelScope.launch {
            val apiClient = AnimeTrendingRepository.getClient()
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