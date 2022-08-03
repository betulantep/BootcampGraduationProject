package com.betulantep.bootcampgraduationproject.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.data.entity.Basket
import com.betulantep.bootcampgraduationproject.data.entity.Food
import com.betulantep.bootcampgraduationproject.data.entity.Quantity
import com.betulantep.bootcampgraduationproject.databinding.FoodRowLayoutBinding
import com.betulantep.bootcampgraduationproject.databinding.RowLayoutBasketBinding
import com.betulantep.bootcampgraduationproject.ui.basket.BasketViewModel
import com.betulantep.bootcampgraduationproject.ui.detail.DetailViewModel
import com.betulantep.bootcampgraduationproject.ui.home.HomeFragmentDirections
import com.betulantep.bootcampgraduationproject.utils.actionFragment
import java.util.ArrayList

class BasketAdapter(
    var basketList: List<Basket>,
    var viewModel: BasketViewModel
) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    class BasketViewHolder(var binding: RowLayoutBasketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(basket: Basket) {
            binding.basket = basket
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view: RowLayoutBasketBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_layout_basket, parent, false
        )
        return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) = with(holder) {
        val basket = basketList[position]
        bind(basket)
        binding.ivFoodDelete.setOnClickListener {
            viewModel.deleteFood(basket.basket_food_id,viewModel.username)
        }
    }

    override fun getItemCount(): Int = basketList.size
}