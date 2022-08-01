package com.betulantep.bootcampgraduationproject.ui.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.databinding.FragmentSignInBinding
import com.betulantep.bootcampgraduationproject.databinding.FragmentSignUpBinding
import com.betulantep.bootcampgraduationproject.ui.onboarding.OnBoardingFragmentDirections
import com.betulantep.bootcampgraduationproject.ui.signin.SignInFragmentDirections
import com.betulantep.bootcampgraduationproject.ui.signin.SignInViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temViewModel : SignUpViewModel by viewModels()
        viewModel = temViewModel
        auth = Firebase.auth
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up, container, false)
        binding.signUpFragment = this
        binding.viewModel = viewModel
        confirmPasswordFocusListener()
        return binding.root
    }
    fun signUpClicked(view: View,email:String,password:String){
        val emailHelperText = binding.signUpTextFieldEmail.helperText
        val passwordHelperText = binding.signUpTextFieldPassword.helperText
        val confirmPasswordHelperText = binding.signUpTextFieldConfirmPassword.helperText
        if(emailHelperText == null && passwordHelperText == null && confirmPasswordHelperText == null){
            viewModel.signUp(auth,view,email,password)
        }else{
            Toast.makeText(requireContext(),"Please check Email or Password",Toast.LENGTH_SHORT).show()
        }
    }
    private fun confirmPasswordFocusListener(){
        binding.etSignUpConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                val password = binding.etSignUpPassword.text.toString()
                val confirmPassword = binding.etSignUpConfirmPassword.text.toString()
                if(password != confirmPassword){
                    binding.signUpTextFieldConfirmPassword.helperText = "Not Equals Passwords"
                }else{
                    binding.signUpTextFieldConfirmPassword.helperText = null
                }
            }
        })
    }
}