package com.betulantep.bootcampgraduationproject.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.databinding.FragmentBasketBinding
import com.betulantep.bootcampgraduationproject.ui.adapter.BasketAdapter
import com.betulantep.bootcampgraduationproject.utils.actionFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BasketFragment : Fragment() {
    private lateinit var binding: FragmentBasketBinding
    private lateinit var viewModel: BasketViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_basket, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.basketFragment = this

        binding.topAppBarBasket.setNavigationOnClickListener {
            Navigation.actionFragment(it, BasketFragmentDirections.actionBasketFragmentToHomeFragment())
        }
        observe()
        return binding.root
    }

    private fun observe(){
        viewModel.basketFoodList.observe(viewLifecycleOwner){
            if(it.isNullOrEmpty()){
                binding.basketAdapter = BasketAdapter(arrayListOf(),viewModel)
            }else{
                binding.basketAdapter = BasketAdapter(it,viewModel)
                binding.lottieEmptyBasket.visibility = View.GONE
            }
        }
    }

    fun clickedBasketConfirm(){
            binding.rvBasketFragment.visibility = View.GONE
            binding.lottieAwait.visibility = View.VISIBLE
            repeat(3){
                binding.lottieAwait.playAnimation()
            }
            lifecycleScope.launch {
                delay(2000)
                binding.lottieAwait.visibility = View.GONE
                binding.lottieConfirmed.visibility = View.VISIBLE
                binding.lottieConfirmed.playAnimation()
                delay(5000)
                binding.lottieEmptyBasket.visibility = View.VISIBLE

            }
            val list = viewModel.basketFoodList.value
            if (list != null) {
                for (basket in list){
                    viewModel.deleteFood(basket.basket_food_id,viewModel.username)
                }
            }
            viewModel.viewModelTotal.value = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : BasketViewModel by viewModels()
        viewModel = tempViewModel
    }

}