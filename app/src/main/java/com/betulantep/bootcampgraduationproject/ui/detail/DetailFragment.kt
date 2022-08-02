package com.betulantep.bootcampgraduationproject.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.databinding.FragmentDetailBinding
import com.betulantep.bootcampgraduationproject.utils.actionFragment

class DetailFragment : Fragment() {
    private lateinit var binding : FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        binding.topAppBar.setNavigationOnClickListener {
            Navigation.actionFragment(it,DetailFragmentDirections.actionDetailFragmentToHomeFragment())
        }
        //return inflater.inflate(R.layout.fragment_detail, container, false)
        return binding.root
    }
}