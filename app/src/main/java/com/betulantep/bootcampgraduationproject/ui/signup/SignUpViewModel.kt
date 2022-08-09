package com.betulantep.bootcampgraduationproject.ui.signup

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.utils.actionFragment
import com.betulantep.bootcampgraduationproject.utils.showToast
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel: ViewModel() {
    fun signUp(auth: FirebaseAuth,view:View, email:String,password:String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                Navigation.actionFragment(view,SignUpFragmentDirections.actionSignUpFragmentToWelcomeFragment())
            }
            .addOnFailureListener {
                showToast(view.context, R.string.hatali_giris)
            }
    }
    fun goToSignIn(view: View){
        Navigation.actionFragment(view, SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
    }
}