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
import com.betulantep.bootcampgraduationproject.ui.favorite.FavoriteViewModel
import com.betulantep.bootcampgraduationproject.ui.home.HomeFragmentDirections
import com.betulantep.bootcampgraduationproject.utils.actionFragment
import com.betulantep.bootcampgraduationproject.utils.changeColorFavorite

class FoodAdapter(var foodList: List<Food>,var favoriteList: List<Favorite>,var favoriteViewModel: FavoriteViewModel): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    private var currentFavorite: Favorite? = null
    class FoodViewHolder(var binding: FoodRowLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food){
            binding.food = food
            binding.favoriteBoolean = false // home da gözükmesini düzeltti
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
        var isFavorite = checkFavorite(food)
        binding.favoriteBoolean = isFavorite

        binding.ivFavoriteImage.setOnClickListener {
            when(isFavorite) {
                true -> {
                    favoriteViewModel.deleteFavoriteFood(currentFavorite!!)
                    binding.ivFavoriteImage.changeColorFavorite(R.color.mediumGray)
                }
                false -> {
                    favoriteViewModel.insertFavoriteFood(Favorite(0, food, favoriteViewModel.userName))
                    binding.ivFavoriteImage.changeColorFavorite(R.color.red)
                }
            }
            isFavorite = !isFavorite
        }

        binding.cardviewFoodHome.setOnClickListener {
            Navigation.actionFragment(it,HomeFragmentDirections.actionHomeFragmentToDetailFragment(food = food))
        }
    }

    override fun getItemCount(): Int = foodList.size

    private fun checkFavorite(food: Food): Boolean{
        favoriteList.forEach { favorite->
            if(favorite.food.foodId == food.foodId){
                currentFavorite = favorite
                return true
            }
        }
        return false
    }
}

interface ItemClickListener {
    fun onItemClick(food: Food)
}