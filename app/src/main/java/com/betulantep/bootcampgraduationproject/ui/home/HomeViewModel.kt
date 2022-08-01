package com.betulantep.bootcampgraduationproject.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betulantep.bootcampgraduationproject.data.entity.Food
import com.betulantep.bootcampgraduationproject.data.repo.FoodRepository
import com.betulantep.bootcampgraduationproject.utils.AppPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var appPref: AppPref, var foodRepo: FoodRepository) :
    ViewModel() {

    var foodsList = MutableLiveData<List<Food>>()

    init {
        loadFoods()
        foodsList = foodRepo.getFoods()
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


}