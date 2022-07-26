package com.betulantep.bootcampgraduationproject.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betulantep.bootcampgraduationproject.utils.AppPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var appPref: AppPref): ViewModel() {
    fun putOnBoarding(){
        viewModelScope.launch {
            appPref.putOnBoardingShow(false)
        }
    }
}