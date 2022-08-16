package com.betulantep.bootcampgraduationproject.ui.detail

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.data.entity.Basket
import com.betulantep.bootcampgraduationproject.data.entity.Food
import com.betulantep.bootcampgraduationproject.data.repo.BasketRepository
import com.betulantep.bootcampgraduationproject.utils.showSnackBar
import com.betulantep.bootcampgraduationproject.utils.showToast
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
    fun clickedAddToCart(view:View, food: Food,quantity: Int) {
        if(quantity == 0){
            showToast(view.context, R.string.lutfen_urun_adeti_seçiniz)
        }else{
            loadAllFoodBasket()//detaydan çıkmadan tekrar ürün eklerse 2 kere eklenmesin diye
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
            showSnackBar(view, R.string.urun_sepete_eklendi)
        }
    }
}