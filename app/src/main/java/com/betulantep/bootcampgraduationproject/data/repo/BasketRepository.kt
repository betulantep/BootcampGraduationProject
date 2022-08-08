package com.betulantep.bootcampgraduationproject.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.betulantep.bootcampgraduationproject.data.entity.Basket
import com.betulantep.bootcampgraduationproject.data.entity.BasketResponse
import com.betulantep.bootcampgraduationproject.data.entity.CRUDResponse
import com.betulantep.bootcampgraduationproject.retrofit.FoodDao
import com.betulantep.bootcampgraduationproject.utils.AppPref
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class BasketRepository @Inject constructor(var foodDao: FoodDao, var appPref: AppPref) {
    var username : String
    lateinit var auth: FirebaseAuth
    val basketFoodList : MutableLiveData<List<Basket>>

    init {
        basketFoodList = MutableLiveData()
        auth = Firebase.auth
        username = auth.currentUser!!.email.toString()
    }

    fun getBasketFood(): MutableLiveData<List<Basket>> {
        return basketFoodList
    }

    fun getFoodQuantity(){
        foodDao.getAllFoodBasket(username).enqueue(object : Callback<BasketResponse> {
            override fun onResponse(
                call: Call<BasketResponse>?,
                response: Response<BasketResponse>
            ) {
                val list = response.body().basketFoods
                basketFoodList.value = list
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