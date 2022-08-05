package com.betulantep.bootcampgraduationproject.ui.signin

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.databinding.FragmentOnboardingBinding
import com.betulantep.bootcampgraduationproject.databinding.FragmentSignInBinding
import com.betulantep.bootcampgraduationproject.ui.onboarding.OnBoardingFragmentDirections
import com.betulantep.bootcampgraduationproject.utils.actionFragment
import com.betulantep.bootcampgraduationproject.utils.showToast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temViewModel : SignInViewModel by viewModels()
        viewModel = temViewModel
        auth = Firebase.auth
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_in, container, false)
        binding.signInFragment = this
        binding.viewModel = viewModel
        passwordFocusListener()
        return binding.root
    }

    fun signInClicked(view: View,email:String,password:String){
        val emailHelperText = binding.signInTextFieldEmail.helperText
        val passwordHelperText = binding.signInTextFieldPassword.helperText
        if(emailHelperText == null && passwordHelperText == null){
            viewModel.signIn(auth,view,email,password)
        }else{
            showToast(requireContext(),R.string.required_email_and_password)
        }
    }


    private fun passwordFocusListener(){
       binding.etSignInPassword.addTextChangedListener(object : TextWatcher {
           override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
           override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
           override fun afterTextChanged(p0: Editable?) {
               binding.signInTextFieldPassword.helperText = validPassword()
           }
       })
    }

    private fun validPassword(): String? {
        val password = binding.etSignInPassword.text.toString()
        if(password.isEmpty()){
            return "Şifre Gerekli"
        }
        return null
    }
}