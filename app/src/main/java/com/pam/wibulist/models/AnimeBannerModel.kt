package com.pam.wibulist.models

import com.google.gson.annotations.SerializedName

class AnimeBannerModel(

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("imgUrl")
    val imgUrl: String,

    @SerializedName("genre")
    val genre: String,

    @SerializedName("Deskripsi")
    val Deskripsi: String,

    @SerializedName("rating")
    val rating: String,

    @SerializedName("release")
    val release: String,

    @SerializedName("imgBanner")
    val imgBanner: String


    )