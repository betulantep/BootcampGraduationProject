package com.betulantep.bootcampgraduationproject.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.databinding.FragmentOnboardingBinding
import com.betulantep.bootcampgraduationproject.utils.actionFragment

class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding
    private val viewModel : OnBoardingViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_onboarding, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
}