package com.betulantep.bootcampgraduationproject.ui.signin

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.ui.signup.SignUpFragmentDirections
import com.betulantep.bootcampgraduationproject.utils.actionFragment
import com.betulantep.bootcampgraduationproject.utils.showToast
import com.google.firebase.auth.FirebaseAuth

class SignInViewModel: ViewModel() {

    fun signIn(auth: FirebaseAuth,view:View, email:String,password:String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                Navigation.actionFragment(view, SignInFragmentDirections.actionSignInFragmentToHomeFragment())
            }
            .addOnFailureListener {
                showToast(view.context, R.string.hatali_giris)
            }
    }
    fun goToSignUp(view: View){
        Navigation.actionFragment(view,SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
    }
}