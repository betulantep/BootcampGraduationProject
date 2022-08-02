package com.betulantep.bootcampgraduationproject.ui.detail

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {
    val quantity = MutableLiveData(0)
    val subTotal = MutableLiveData(0)

    fun clickedAddToCart(view: View,viewLinear: View,foodPrice:Int){
        view.visibility = View.GONE
        viewLinear.visibility = View.VISIBLE
        quantity.value = quantity.value!! + 1
        subTotal.value = quantity.value!! * foodPrice
        Log.e("detay","1 adet eklendi")
    }

    fun clickedAdd(view: View,foodPrice:Int){
        quantity.value = quantity.value!! + 1
        subTotal.value = quantity.value!! * foodPrice
        Log.e("detay","1 adet eklendi")
    }

    fun clickedDelete(view: View,viewLinear: View,viewCart:View,foodPrice:Int){
        if(quantity.value!!-1 != 0){
            quantity.value = quantity.value!! - 1
            subTotal.value = quantity.value!! * foodPrice
            Log.e("detay","1 adet silindi")
        }else{
            quantity.value = quantity.value!! - 1
            subTotal.value = quantity.value!! * foodPrice
            Log.e("detay","1 adet silindi")
            viewLinear.visibility = View.GONE
            viewCart.visibility = View.VISIBLE
        }
    }

}