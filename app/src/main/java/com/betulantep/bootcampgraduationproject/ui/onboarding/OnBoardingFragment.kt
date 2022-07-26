package com.betulantep.bootcampgraduationproject.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.databinding.FragmentOnboardingBinding

class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(layoutInflater)
        //return inflater.inflate(R.layout.fragment_onboarding, container, false)

        binding.buttonIn.setOnClickListener {
            Navigation.findNavController(it).navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToSignInFragment())
        }

        binding.buttonUp.setOnClickListener {
            Navigation.findNavController(it).navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToSignUpFragment())
        }
        return binding.root
    }
}