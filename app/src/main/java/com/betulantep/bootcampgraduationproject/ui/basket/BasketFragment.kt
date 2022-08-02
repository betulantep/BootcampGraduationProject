package com.betulantep.bootcampgraduationproject.ui.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.databinding.FragmentBasketBinding
import com.betulantep.bootcampgraduationproject.databinding.FragmentDetailBinding
import com.betulantep.bootcampgraduationproject.ui.detail.DetailFragmentDirections
import com.betulantep.bootcampgraduationproject.utils.actionFragment

class BasketFragment : Fragment() {
    private lateinit var binding: FragmentBasketBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBasketBinding.inflate(layoutInflater)
        binding.topAppBarBasket.setNavigationOnClickListener {
            Navigation.actionFragment(it, BasketFragmentDirections.actionBasketFragmentToHomeFragment())
        }
        //return inflater.inflate(R.layout.fragment_basket, container, false)
        return binding.root
    }
}