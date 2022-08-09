package com.betulantep.bootcampgraduationproject.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.betulantep.bootcampgraduationproject.data.entity.Basket
import com.betulantep.bootcampgraduationproject.data.entity.Food
import com.betulantep.bootcampgraduationproject.data.repo.BasketRepository
import com.betulantep.bootcampgraduationproject.utils.AppPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var basketRepo: BasketRepository,var appPref: AppPref) : ViewModel() {
    var basketFoodList = MutableLiveData<List<Basket>>()
    var username : String

    fun usernameGet(): String{
        CoroutineScope(Dispatchers.Main).launch {
            username = appPref.getUsername()
        }
        return username
    }
    init {
        loadAllFoodBasket()
        basketFoodList = basketRepo.getBasketFood()
        username = usernameGet()
    }

    fun loadAllFoodBasket() {
        basketRepo.getFoodQuantity()

    }
    fun clickedAddToCart(food: Food,quantity: Int) {
        if(!basketFoodList.value.isNullOrEmpty()){
            for(basketFood in basketFoodList.value!!){
                if(basketFood.basket_food_name == food.foodName){
                    basketRepo.deleteFoodBasket(basketFood.basket_food_id,username)
                }
            }
        }
        basketRepo.addFoodBasket(
            food.foodName,
            food.foodImageName,
            food.foodPrice,
            quantity,
            username
        )
        Log.e("detay", "1 adet eklendi")
    }
}