package com.betulantep.bootcampgraduationproject.ui.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.betulantep.bootcampgraduationproject.data.entity.Basket
import com.betulantep.bootcampgraduationproject.data.repo.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(var basketRepo: BasketRepository): ViewModel() {
    var basketFoodList = MutableLiveData<List<Basket>>()
    var username = basketRepo.username
    var viewModelSubTotal = MutableLiveData<Int>(0)

    init {
        loadAllFoodBasket()
        basketFoodList = basketRepo.getBasketFood()
    }

    fun loadAllFoodBasket(){
        basketRepo.getFoodQuantity()
    }

    fun deleteFood(foodId:Int,username:String) {
        basketRepo.deleteFoodBasket(foodId,username)
        if(basketFoodList.value!!.size -1 == 0){
            basketFoodList.value = emptyList()
        }
    }
}