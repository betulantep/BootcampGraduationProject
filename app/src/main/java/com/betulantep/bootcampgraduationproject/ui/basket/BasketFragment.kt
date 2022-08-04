package com.betulantep.bootcampgraduationproject.ui.basket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.data.entity.Basket
import com.betulantep.bootcampgraduationproject.data.entity.Quantity
import com.betulantep.bootcampgraduationproject.databinding.FragmentBasketBinding
import com.betulantep.bootcampgraduationproject.databinding.FragmentDetailBinding
import com.betulantep.bootcampgraduationproject.ui.adapter.BasketAdapter
import com.betulantep.bootcampgraduationproject.ui.detail.DetailFragmentDirections
import com.betulantep.bootcampgraduationproject.ui.detail.DetailViewModel
import com.betulantep.bootcampgraduationproject.ui.welcome.WelcomeFragmentDirections
import com.betulantep.bootcampgraduationproject.utils.actionFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.ArrayList

@AndroidEntryPoint
class BasketFragment : Fragment() {
    private lateinit var binding: FragmentBasketBinding
    private lateinit var viewModel: BasketViewModel
    private lateinit var auth: FirebaseAuth
    var subTotal = 0
    var total = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_basket, container, false)
        auth = Firebase.auth
        var userName = auth.currentUser!!.email.toString()
        binding.topAppBarBasket.setNavigationOnClickListener {
            Navigation.actionFragment(it, BasketFragmentDirections.actionBasketFragmentToHomeFragment())
        }
        viewModel.basketFoodList.observe(viewLifecycleOwner){
            if(it.isNullOrEmpty()){
                binding.basketAdapter = BasketAdapter(arrayListOf(),viewModel)
            }else{
                binding.basketAdapter = BasketAdapter(it,viewModel)
                binding.lottieEmptyBasket.visibility = View.GONE
            }
        }
        viewModel.viewModelSubTotal.observe(viewLifecycleOwner){
            Log.e("asd",total.toString())
            binding.tvBasketFoodTotal.text = "₺$it"
        }

        binding.btnBasketConfirm.setOnClickListener {
            binding.rvBasketFragment.visibility = View.GONE
            binding.lottieAwait.visibility = View.VISIBLE
            binding.lottieAwait.playAnimation()
            binding.lottieAwait.playAnimation()
            binding.lottieAwait.playAnimation()
            lifecycleScope.launch {
                delay(2000)
                binding.lottieAwait.visibility = View.GONE
                binding.lottieConfirmed.visibility = View.VISIBLE
                binding.lottieConfirmed.playAnimation()

            }
            val list = viewModel.basketFoodList.value
            if (list != null) {
                for (basket in list){
                    viewModel.deleteFood(basket.basket_food_id,userName)
                }
            }
            binding.tvBasketFoodTotal.text = "₺0"


        }

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : BasketViewModel by viewModels()
        viewModel = tempViewModel
    }

}