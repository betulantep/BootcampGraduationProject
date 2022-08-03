package com.betulantep.bootcampgraduationproject.ui.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.data.entity.Basket
import com.betulantep.bootcampgraduationproject.data.entity.Quantity
import com.betulantep.bootcampgraduationproject.databinding.FragmentBasketBinding
import com.betulantep.bootcampgraduationproject.databinding.FragmentDetailBinding
import com.betulantep.bootcampgraduationproject.ui.adapter.BasketAdapter
import com.betulantep.bootcampgraduationproject.ui.detail.DetailFragmentDirections
import com.betulantep.bootcampgraduationproject.ui.detail.DetailViewModel
import com.betulantep.bootcampgraduationproject.utils.actionFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class BasketFragment : Fragment() {
    private lateinit var binding: FragmentBasketBinding
    private lateinit var viewModel: BasketViewModel
    var quantityBasket = 0
    var quantitySubtotal = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_basket, container, false)
        binding.topAppBarBasket.setNavigationOnClickListener {
            Navigation.actionFragment(it, BasketFragmentDirections.actionBasketFragmentToHomeFragment())
        }
        viewModel.basketFoodList.observe(viewLifecycleOwner){
           binding.basketAdapter = BasketAdapter(it,viewModel)
        }

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : BasketViewModel by viewModels()
        viewModel = tempViewModel
    }

}