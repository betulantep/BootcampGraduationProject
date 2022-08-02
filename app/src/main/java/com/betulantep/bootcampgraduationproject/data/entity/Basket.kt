package com.betulantep.bootcampgraduationproject.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Basket(
    @SerializedName("sepet_yemek_id")
    val basket_food_id: Int,
    @SerializedName("yemek_adi")
    val basket_food_name: String,
    @SerializedName("yemek_resim_adi")
    val basket_image_name: String,
    @SerializedName("yemek_fiyat")
    val basket_food_price: Int,
    @SerializedName("yemek_siparis_adet")
    val basket_order_quantity: Int,
    @SerializedName("kullanici_adi")
    val user_name: String

) : Serializable {}
