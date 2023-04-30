package com.pam.wibulist.repositories

import com.pam.wibulist.models.AnimePopularModel
import com.pam.wibulist.models.AnimeTrendModel
import com.pam.wibulist.models.AnimeTrendingModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AnimeTrendingRepository {
    @GET("Trending")
    suspend fun getTrendingAnime(): List<AnimeTrendingModel>

    companion object {
        var _apiClient: AnimeTrendingRepository? = null

        fun getClient(): AnimeTrendingRepository {
            if (_apiClient == null) {
                _apiClient = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/anggasta-nau/TrendAniJson/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AnimeTrendingRepository::class.java)
            }

            return _apiClient!!
        }
    }
}