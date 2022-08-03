package com.betulantep.bootcampgraduationproject.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.data.entity.Food
import com.betulantep.bootcampgraduationproject.data.entity.Quantity
import com.betulantep.bootcampgraduationproject.databinding.FragmentDetailBinding
import com.betulantep.bootcampgraduationproject.utils.actionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding : FragmentDetailBinding
    private lateinit var navArgs: DetailFragmentArgs
    private lateinit var viewModel: DetailViewModel
    var quantity = 0
    var subTotal = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail, container, false)
        binding.topAppBar.setNavigationOnClickListener {
            Navigation.actionFragment(it,DetailFragmentDirections.actionDetailFragmentToHomeFragment())
        }
        val bundle : DetailFragmentArgs by navArgs()
        navArgs = bundle
        binding.food = navArgs.food
        binding.viewModel = viewModel
        binding.detailFragment = this

        return binding.root
    }

    fun clickedAddToCart(food: Food,quantity: Int){
        viewModel.clickedAddToCart(food,quantity)
    }
    fun clickedAdd(){
        quantity = binding.tvDetailQuantity.text.toString().toInt()
        quantity++
        subTotal = quantity * navArgs.food.foodPrice
        binding.tvDetailSubTotal.text = "₺$subTotal"
        binding.tvDetailQuantity.text = quantity.toString()

    }
    fun clickedDelete(){
        quantity = binding.tvDetailQuantity.text.toString().toInt()
        if(quantity!=0){
            quantity--
            subTotal = quantity * navArgs.food.foodPrice
            binding.tvDetailSubTotal.text = "₺$subTotal"
            binding.tvDetailQuantity.text = quantity.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : DetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel.getQuantity(navArgs.food)
    }
}