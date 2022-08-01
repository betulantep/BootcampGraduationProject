package com.betulantep.bootcampgraduationproject.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.betulantep.bootcampgraduationproject.data.entity.Food
import com.betulantep.bootcampgraduationproject.data.entity.FoodResponse
import com.betulantep.bootcampgraduationproject.retrofit.FoodDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FoodRepository @Inject constructor(var foodDao: FoodDao) {
    var foodsList : MutableLiveData<List<Food>>
    var foodLoading: MutableLiveData<Boolean> = MutableLiveData()
    init {
        foodsList = MutableLiveData()
    }

    fun getFoods() : MutableLiveData<List<Food>>{
        return foodsList
    }
    fun getLoading() : MutableLiveData<Boolean>{
        return foodLoading
    }

    fun getAllFoods(){
        foodLoading.value = true
        foodDao.getAllFood().enqueue(object : Callback<FoodResponse>{
            override fun onResponse(call: Call<FoodResponse>?, response: Response<FoodResponse>) {
                val list = response.body().foods
                foodsList.value = list
                Log.e("asd","getallfood")
                foodLoading.value = false
            }

            override fun onFailure(call: Call<FoodResponse>?, t: Throwable?) {}

        })
    }
    fun searchFood(searchWord:String){
        foodLoading.value = true
        foodDao.getAllFood().enqueue(object : Callback<FoodResponse>{
            override fun onResponse(call: Call<FoodResponse>?, response: Response<FoodResponse>) {
                val list = response.body().foods
                foodsList.value = list
                val searchList = ArrayList<Food>()
                for(food in list){
                    if(food.foodName.lowercase().contains(searchWord.lowercase())){
                        searchList.add(food)
                    }
                }
                foodsList.value = searchList
                foodLoading.value = false
            }

            override fun onFailure(call: Call<FoodResponse>?, t: Throwable?) {}

        })
    }

}