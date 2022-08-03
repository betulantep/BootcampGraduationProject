package com.betulantep.bootcampgraduationproject.ui.basket

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.betulantep.bootcampgraduationproject.data.entity.Basket
import com.betulantep.bootcampgraduationproject.data.entity.Food
import com.betulantep.bootcampgraduationproject.data.entity.Quantity
import com.betulantep.bootcampgraduationproject.data.repo.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(var basketRepo: BasketRepository): ViewModel() {
    var basketFoodList = MutableLiveData<List<Basket>>()
    val username = basketRepo.usernameGet()

    init {
        loadAllFoodBasket()
        basketFoodList = basketRepo.getBasketFood()
    }

    fun loadAllFoodBasket(){
        basketRepo.getFoodQuantity()
    }

    fun deleteFood(foodId:Int,username:String) {
        Log.e("usernamee",username)
        basketRepo.deleteFoodBasket(foodId,username)
    }



}