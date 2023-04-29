package com.pam.wibulist.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pam.wibulist.repositories.AnimeRepository
import kotlinx.coroutines.launch

class AnimeTrendViewModel: ViewModel() {
    private var _animeTrendList = mutableStateListOf<AnimeTrendModel>()

    var errorMessage: String by mutableStateOf("")
    val animeTrendList: List<AnimeTrendModel>
        get() = _animeTrendList

    fun getAnimeTrendList() {
        viewModelScope.launch {
            val apiClient = AnimeRepository.getClient()
            try {
                _animeTrendList.clear()
                _animeTrendList.addAll(apiClient.getTrendAnime())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }

}