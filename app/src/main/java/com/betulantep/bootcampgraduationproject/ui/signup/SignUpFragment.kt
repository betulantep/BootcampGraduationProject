package com.betulantep.bootcampgraduationproject.ui.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        binding = FragmentSignUpBinding.inflate(layoutInflater)

        return binding.root
        //return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonKaydet.setOnClickListener {
            val email = binding.etEmailUp.text.toString()
            val password = binding.etPasswordUp.text.toString()
            viewModel.signUp(auth,it,email,password)
        }
    }
}