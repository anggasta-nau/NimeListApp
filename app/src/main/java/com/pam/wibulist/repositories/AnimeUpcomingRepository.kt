package com.pam.wibulist.repositories

import com.pam.wibulist.models.AnimeActionModel
import com.pam.wibulist.models.AnimeModel
import com.pam.wibulist.models.AnimeTrendModel
import com.pam.wibulist.models.AnimeUpcomingModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AnimeUpcomingRepository {

    @GET("List")
    suspend fun getUpcomingAnime(): List<AnimeUpcomingModel>

    companion object {
        var _apiClient: AnimeUpcomingRepository? = null

        fun getClient(): AnimeUpcomingRepository {
            if (_apiClient == null) {
                _apiClient = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/anggasta-nau/UpcomingAniJson/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AnimeUpcomingRepository::class.java)
            }

            return _apiClient!!
        }
    }
}