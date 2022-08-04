package com.betulantep.bootcampgraduationproject.data.repo

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.betulantep.bootcampgraduationproject.data.entity.*
import com.betulantep.bootcampgraduationproject.retrofit.FoodDao
import com.betulantep.bootcampgraduationproject.utils.AppPref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject


class BasketRepository @Inject constructor(var foodDao: FoodDao, var appPref: AppPref) {
    var username : String


    val basketFoodList : MutableLiveData<List<Basket>>

    init {
        basketFoodList = MutableLiveData()
        username = usernameGet()

    }

    fun usernameGet(): String{
        CoroutineScope(Dispatchers.Main).launch {
            username = appPref.getUsername()
        }
       return username
    }
    fun getBasketFood(): MutableLiveData<List<Basket>> {
        return basketFoodList
    }

    fun getFoodQuantity(){
        username = usernameGet()
    //    Log.e("asd",username)
        foodDao.getAllFoodBasket("betul@gmail.com").enqueue(object : Callback<BasketResponse> {
            override fun onResponse(
                call: Call<BasketResponse>?,
                response: Response<BasketResponse>
            ) {
                val list = response.body().basketFoods
                basketFoodList.value = list
                Log.e("asd",basketFoodList.value.toString())
            }
            override fun onFailure(call: Call<BasketResponse>?, t: Throwable?) {}
        })
    }


    fun addFoodBasket(
        foodName: String,
        foodImageName: String,
        foodPrice: Int,
        foodQuantity: Int,
        username: String
    ) {
        foodDao.addFoodToBasket(foodName, foodImageName, foodPrice, foodQuantity, username)
            .enqueue(object : Callback<CRUDResponse> {
                override fun onResponse(
                    call: Call<CRUDResponse>?,
                    response: Response<CRUDResponse>
                ) {
                    val success = response.body().success
                    val message = response.body().message
                    Log.e("Basket Add", "$success - $message")
                }

                override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {}

            })
    }

    fun deleteFoodBasket(foodId: Int, username: String) {
        foodDao.deleteFoodToBasket(foodId, username).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>) {
                val success = response.body().success
                val message = response.body().message
                Log.e("Basket Delete", "$success - $message")
                getFoodQuantity()
            }

            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {}

        })
    }
}