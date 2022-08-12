package com.betulantep.bootcampgraduationproject.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.betulantep.bootcampgraduationproject.data.entity.Basket
import com.betulantep.bootcampgraduationproject.data.entity.Food
import com.betulantep.bootcampgraduationproject.data.repo.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var basketRepo: BasketRepository) : ViewModel() {
    var basketFoodList = MutableLiveData<List<Basket>>()
    var userName = basketRepo.username

    init {
        loadAllFoodBasket()
        basketFoodList = basketRepo.getBasketFood()
    }

    fun processSubTotal(quantity:Int,price:Int):Int{
        return basketRepo.processSubTotal(quantity,price)
    }

    fun loadAllFoodBasket() {
        basketRepo.getFoodQuantity()

    }
    fun clickedAddToCart(food: Food,quantity: Int) {
        if(!basketFoodList.value.isNullOrEmpty()){
            for(basketFood in basketFoodList.value!!){
                if(basketFood.basket_food_name == food.foodName){
                    basketRepo.deleteFoodBasket(basketFood.basket_food_id,userName)
                }
            }
        }
        basketRepo.addFoodBasket(
            food.foodName,
            food.foodImageName,
            food.foodPrice,
            quantity,
            userName
        )
    }
}