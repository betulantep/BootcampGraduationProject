package com.betulantep.bootcampgraduationproject.data.entity


import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("success")
    val success: Int,
    @SerializedName("yemekler")
    val foods: List<Food>
)