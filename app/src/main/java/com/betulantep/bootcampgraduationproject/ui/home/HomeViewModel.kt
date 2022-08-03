package com.betulantep.bootcampgraduationproject.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betulantep.bootcampgraduationproject.data.entity.Food
import com.betulantep.bootcampgraduationproject.data.repo.FoodRepository
import com.betulantep.bootcampgraduationproject.utils.AppPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var appPref: AppPref,
    var foodRepo: FoodRepository
) : ViewModel() {

    var foodsList = MutableLiveData<List<Food>>()
    var foodLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loadFoods()
        foodsList = foodRepo.getFoods()
        foodLoading = foodRepo.getLoading()
    }

    fun loadFoods() {
        foodRepo.getAllFoods()
    }

    fun searchFood(searchWord: String) {
        foodRepo.searchFood(searchWord)
    }

    fun putOnBoarding() {
        viewModelScope.launch {
            appPref.putOnBoardingShow(false)
        }
    }
   fun putUsername(username:String) {
        viewModelScope.launch {
            appPref.putUsername(username)
        }
    }


}