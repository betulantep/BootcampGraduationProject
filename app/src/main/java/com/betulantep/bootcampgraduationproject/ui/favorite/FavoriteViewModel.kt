package com.betulantep.bootcampgraduationproject.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.betulantep.bootcampgraduationproject.data.entity.Favorite
import com.betulantep.bootcampgraduationproject.data.repo.BasketRepository
import com.betulantep.bootcampgraduationproject.data.repo.FoodRepository
import com.betulantep.bootcampgraduationproject.utils.AppPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(var foodRepo: FoodRepository,var basketRepo: BasketRepository): ViewModel() {
    var readFavoriteFood: LiveData<List<Favorite>> = foodRepo.readFavoriteFood().asLiveData()
    var userName = basketRepo.username

    fun insertFavoriteFood(favorite: Favorite) =
        viewModelScope.launch(Dispatchers.IO) {
            foodRepo.insertFavoriteFood(favorite)
        }

    fun deleteFavoriteFood(favorite: Favorite) =
        viewModelScope.launch(Dispatchers.IO) {
            foodRepo.deleteFavoriteFood(favorite)
        }
}