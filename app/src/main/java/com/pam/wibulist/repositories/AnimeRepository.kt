package com.pam.wibulist.repositories

import com.pam.wibulist.models.AnimeActionModel
import com.pam.wibulist.models.AnimeModel
import com.pam.wibulist.models.AnimeTrendModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AnimeRepository {

    @GET("Genres_Action")
    suspend fun getActionAnime():List<AnimeActionModel>

    @GET("Trend")
    suspend fun getTrendAnime():List<AnimeTrendModel>


    @GET("List")
    suspend fun getAnime(): List<AnimeModel>

    companion object {
        var _apiClient: AnimeRepository? = null

        fun getClient(): AnimeRepository {
            if (_apiClient == null) {
                _apiClient = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/anggasta-nau/AnimeRepo/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AnimeRepository::class.java)
            }

            return _apiClient!!
        }
    }
}