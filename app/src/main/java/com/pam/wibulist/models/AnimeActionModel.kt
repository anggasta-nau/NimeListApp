package com.pam.wibulist.models

import com.google.gson.annotations.SerializedName

data class AnimeActionModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("imgUrl")
    val imgUrl: String,

    @SerializedName("Deskripsi")
    val Deskripsi: String,

    @SerializedName("genre")
    val genre: String
)
