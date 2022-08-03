package com.betulantep.bootcampgraduationproject.ui.detail

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betulantep.bootcampgraduationproject.data.entity.Basket
import com.betulantep.bootcampgraduationproject.data.entity.Food
import com.betulantep.bootcampgraduationproject.data.entity.Quantity
import com.betulantep.bootcampgraduationproject.data.repo.BasketRepository
import com.betulantep.bootcampgraduationproject.retrofit.FoodDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var basketRepo: BasketRepository) : ViewModel() {
    var basketFoodList = MutableLiveData<List<Basket>>()
    var quantity = MutableLiveData<Int>(0)
    var subTotal = MutableLiveData<Int>(0)
    var username = basketRepo.usernameGet()

    init {
        loadAllFoodBasket()
        basketFoodList = basketRepo.getBasketFood()
        Log.e("detay", username)
    }

    fun loadAllFoodBasket() {
        basketRepo.getFoodQuantity()

    }
    fun clickedAddToCart(food: Food,quantity: Int) {
        basketRepo.addFoodBasket(
            food.foodName,
            food.foodImageName,
            food.foodPrice,
            quantity,
            username
        )
        Log.e("detay", username)
        Log.e("detay", "1 adet eklendi")
    }
}