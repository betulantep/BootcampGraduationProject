package com.betulantep.bootcampgraduationproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.data.entity.Favorite
import com.betulantep.bootcampgraduationproject.data.entity.Food
import com.betulantep.bootcampgraduationproject.databinding.FoodRowLayoutBinding
import com.betulantep.bootcampgraduationproject.ui.home.HomeFragmentDirections
import com.betulantep.bootcampgraduationproject.utils.actionFragment

class FoodAdapter(var foodList: List<Food>,var favoriteList: List<Favorite>): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    class FoodViewHolder(var binding: FoodRowLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food){
            binding.food = food
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view : FoodRowLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.food_row_layout,parent,false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) = with(holder) {
        val food = foodList[position]
        bind(food)
        favoriteList.forEach {
            if(food.foodName == it.food.foodName){
                binding.favorite = true
            }
        }
        binding.cardviewFoodHome.setOnClickListener {
            Navigation.actionFragment(it,HomeFragmentDirections.actionHomeFragmentToDetailFragment(food = food))
        }
    }

    override fun getItemCount(): Int = foodList.size
}