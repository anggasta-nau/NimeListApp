package com.pam.wibulist.data

data class AnimeDetail(
    val id: Int,
    val title: String,
    val AnimeImageId: Int = 0,
    val Season: String,
    val Status: String,
    val totalEpisode: String,
    val AnimeDesc: String
):java.io.Serializable
