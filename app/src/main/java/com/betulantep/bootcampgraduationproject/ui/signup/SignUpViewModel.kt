package com.betulantep.bootcampgraduationproject.ui.signup

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.betulantep.bootcampgraduationproject.utils.actionFragment
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel: ViewModel() {
    fun signUp(auth: FirebaseAuth,view:View, email:String,password:String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                Navigation.actionFragment(view,SignUpFragmentDirections.actionSignUpFragmentToWelcomeFragment())
            }
            .addOnFailureListener {
                Toast.makeText(view.context,"Hatalı giriş", Toast.LENGTH_SHORT).show()
            }
    }
}