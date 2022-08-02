package com.betulantep.bootcampgraduationproject.data.entity


import com.google.gson.annotations.SerializedName

data class BasketResponse(
    @SerializedName("success")
    val success: Int,
    @SerializedName("sepet_yemekler")
    val inTheBasket: List<Basket>
)