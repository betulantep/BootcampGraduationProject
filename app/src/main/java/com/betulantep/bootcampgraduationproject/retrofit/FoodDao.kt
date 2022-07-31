package com.betulantep.bootcampgraduationproject.retrofit

import com.betulantep.bootcampgraduationproject.data.entity.FoodResponse
import retrofit2.Call
import retrofit2.http.GET

interface FoodDao {
    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php

    @GET("yemekler/tumYemekleriGetir.php")
    fun getAllFood() : Call<FoodResponse>
}