package com.betulantep.bootcampgraduationproject.ui.basket

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.betulantep.bootcampgraduationproject.data.entity.Basket
import com.betulantep.bootcampgraduationproject.data.entity.Food
import com.betulantep.bootcampgraduationproject.data.entity.Quantity
import com.betulantep.bootcampgraduationproject.data.repo.BasketRepository
import com.betulantep.bootcampgraduationproject.utils.AppPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(var basketRepo: BasketRepository,var appPref: AppPref): ViewModel() {
    var basketFoodList = MutableLiveData<List<Basket>>()
    var username : String
    var viewModelSubTotal = MutableLiveData<Int>(0)

    init {
        loadAllFoodBasket()
        basketFoodList = basketRepo.getBasketFood()
        username = usernameGet()
    }
    fun usernameGet(): String{
        CoroutineScope(Dispatchers.Main).launch {
            username = appPref.getUsername()
        }
        return username
    }

    fun loadAllFoodBasket(){
        basketRepo.getFoodQuantity()
    }

    fun deleteFood(foodId:Int,username:String) {
        Log.e("usernamee",username)
        basketRepo.deleteFoodBasket(foodId,username)
    }



}