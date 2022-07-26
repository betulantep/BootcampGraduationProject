package com.betulantep.bootcampgraduationproject.ui.splash

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.betulantep.bootcampgraduationproject.utils.actionFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {
    fun authControl(auth: FirebaseAuth,view:View){
        viewModelScope.launch {
            delay(5000)
            val currentUser = auth.currentUser
            if(currentUser != null){
                Navigation.actionFragment(view, SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }else{
                Navigation.actionFragment(view, SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment())
            }
        }
    }
}