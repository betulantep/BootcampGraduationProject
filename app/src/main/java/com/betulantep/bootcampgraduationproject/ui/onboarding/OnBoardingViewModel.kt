package com.betulantep.bootcampgraduationproject.ui.onboarding

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.betulantep.bootcampgraduationproject.ui.signup.SignUpFragmentDirections
import com.betulantep.bootcampgraduationproject.utils.actionFragment

class OnBoardingViewModel : ViewModel(){
    fun goToSignUp(view:View){
        Navigation.actionFragment(view, OnBoardingFragmentDirections.actionOnBoardingFragmentToSignUpFragment())
    }
    fun goToSignIn(view:View){
        Navigation.actionFragment(view, OnBoardingFragmentDirections.actionOnBoardingFragmentToSignInFragment())
    }
}