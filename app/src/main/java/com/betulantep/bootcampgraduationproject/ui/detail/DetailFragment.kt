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
import com.betulantep.bootcampgraduationproject.databinding.FragmentDetailBinding
import com.betulantep.bootcampgraduationproject.utils.actionFragment

class DetailFragment : Fragment() {
    private lateinit var binding : FragmentDetailBinding
    private val navArgs: DetailFragmentArgs by navArgs()
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail, container, false)
        binding.topAppBar.setNavigationOnClickListener {
            Navigation.actionFragment(it,DetailFragmentDirections.actionDetailFragmentToHomeFragment())
        }

        binding.food = navArgs.food
        binding.viewModel = viewModel
        binding.detailFragment = this
        observe()

        return binding.root
    }

    private fun observe(){
        viewModel.quantity.observe(viewLifecycleOwner){
            binding.quantityResult = it.toString()
        }
        viewModel.subTotal.observe(viewLifecycleOwner){
            binding.subTotalResult = "₺$it"
        }
    }

    fun clickedAddToCart(view: View,viewLinear: View){
        viewModel.clickedAddToCart(view,viewLinear,navArgs.food.foodPrice)
    }
    fun clickedAdd(view: View){
        viewModel.clickedAdd(view,navArgs.food.foodPrice)
    }
    fun clickedDelete(view: View,viewLinear: View,viewCart:View){
        viewModel.clickedDelete(view,viewLinear,viewCart,navArgs.food.foodPrice)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : DetailViewModel by viewModels()
        viewModel = tempViewModel
    }
}