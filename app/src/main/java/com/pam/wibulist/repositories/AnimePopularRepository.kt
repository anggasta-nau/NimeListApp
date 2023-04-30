package com.pam.wibulist.repositories

import com.pam.wibulist.models.AnimePopularModel
import com.pam.wibulist.models.AnimeUpcomingModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AnimePopularRepository {
    @GET("Popular")
    suspend fun getPopularAnime(): List<AnimePopularModel>

    companion object {
        var _apiClient: AnimePopularRepository? = null

        fun getClient(): AnimePopularRepository {
            if (_apiClient == null) {
                _apiClient = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/anggasta-nau/PopularAniJson/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AnimePopularRepository::class.java)
            }

            return _apiClient!!
        }
    }
}