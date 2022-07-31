package com.betulantep.bootcampgraduationproject.ui.splash

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.utils.AppPref
import com.betulantep.bootcampgraduationproject.utils.actionFragment
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(var appPref: AppPref): ViewModel() {

    fun showOnBoarding(view: View,auth: FirebaseAuth){
        viewModelScope.launch {
            delay(500)
            if(appPref.getOnBoardingShow()){
                Navigation.actionFragment(view,SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment())
            }else{
                authControl(auth,view)
            }
        }
    }

    private fun authControl(auth: FirebaseAuth, view:View){
        viewModelScope.launch {
            val currentUser = auth.currentUser
            if(currentUser != null){
                Navigation.actionFragment(view, SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }else{
                Navigation.actionFragment(view, SplashFragmentDirections.actionSplashFragmentToSignInFragment())
            }
        }
    }
}