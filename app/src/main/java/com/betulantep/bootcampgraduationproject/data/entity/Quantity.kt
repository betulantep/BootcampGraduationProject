package com.betulantep.bootcampgraduationproject.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Quantity(
    val food_name: String,
    val food_price: Int,
    val food_image_name:String,
    val quantity:Int,
    val subTotal:Int
): Serializable{}
