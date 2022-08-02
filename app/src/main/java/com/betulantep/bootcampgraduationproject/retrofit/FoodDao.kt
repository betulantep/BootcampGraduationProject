package com.betulantep.bootcampgraduationproject.retrofit

import com.betulantep.bootcampgraduationproject.data.entity.BasketResponse
import com.betulantep.bootcampgraduationproject.data.entity.CRUDResponse
import com.betulantep.bootcampgraduationproject.data.entity.FoodResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodDao {
    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php
    //http://kasimadalan.pe.hu/yemekler/sepettekiYemekleriGetir.php
    //http://kasimadalan.pe.hu/yemekler/sepeteYemekEkle.php
    //http://kasimadalan.pe.hu/yemekler/sepettenYemekSil.php

    @GET("yemekler/tumYemekleriGetir.php")
    fun getAllFood(): Call<FoodResponse>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun getAllFoodBasket(@Field("kullanici_adi") userName: String): Call<BasketResponse>


    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun addFoodToBasket(
        @Field("yemek_adi") food_name: String,
        @Field("yemek_resim_adi") food_image_name: String,
        @Field("yemek_fiyat") food_price: Int,
        @Field("yemek_siparis_adet") food_order_quantity: Int,
        @Field("kullanici_adi") user_name: Int
    ): Call<CRUDResponse>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun deleteFoodToBasket(
        @Field("sepet_yemek_id") basket_food_id: Int,
        @Field("kullanici_adi") user_name: String
    ): Call<CRUDResponse>


}