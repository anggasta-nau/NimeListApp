package com.pam.wibulist.repositories

import com.pam.wibulist.models.AnimeBannerModel
import com.pam.wibulist.models.AnimeFullModel
import com.pam.wibulist.models.AnimeModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AnimeRepository {

    @GET("AnimeRepo/Genres_Action")
    suspend fun getActionAnime():List<AnimeModel>

    @GET("AnimeRepo/Trend")
    suspend fun getTrendAnime():List<AnimeModel>


    @GET("AnimeRepo/List")
    suspend fun getAnime(): List<AnimeModel>

    @GET("PopularAniJson/Popular")
    suspend fun getPopularAnime(): List<AnimeFullModel>

    @GET("UpcomingAniJson/List")
    suspend fun getUpcomingAnime(): List<AnimeFullModel>

    @GET("TrendAniJson/Trend")
    suspend fun getTrendingAnime(): List<AnimeBannerModel>

    @GET("ActionAniJson/Action")
    suspend fun getActAnime(): List<AnimeBannerModel>

    @GET("ComedyAniJson/Comedy")
    suspend fun getComedyAnime(): List<AnimeBannerModel>

    @GET("FantsyAniJson/Fantasy")
    suspend fun getFantasyAnime(): List<AnimeBannerModel>

    @GET("SliceAniJson/Slice")
    suspend fun getSliceAnime(): List<AnimeBannerModel>

    @GET("SignatureAniJson/Banner")
    suspend fun getBannerAnime(): List<AnimeBannerModel>

    @GET("ListsAniJson/Search")
    suspend fun getSearchAnime(): List<AnimeBannerModel>


    companion object {
        var _apiClient: AnimeRepository? = null

        fun getClient(): AnimeRepository {
            if (_apiClient == null) {
                _apiClient = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/anggasta-nau/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AnimeRepository::class.java)
            }

            return _apiClient!!
        }
    }
}