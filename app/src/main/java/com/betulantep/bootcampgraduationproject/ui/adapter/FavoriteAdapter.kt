package com.betulantep.bootcampgraduationproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.betulantep.bootcampgraduationproject.data.entity.Favorite
import com.betulantep.bootcampgraduationproject.databinding.FavoriteRowLayoutBinding
import com.betulantep.bootcampgraduationproject.utils.FoodsDiffUtil

class FavoriteAdapter(var favoriteFoodList : List<Favorite>): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private var selectedFood = arrayListOf<Favorite>()
    class FavoriteViewHolder(var binding: FavoriteRowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = FavoriteRowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int)= with(holder) {
        val currentFood = favoriteFoodList[position]
        binding.favorite = currentFood
        binding.executePendingBindings()
    }

    override fun getItemCount(): Int = favoriteFoodList.size

    fun setData(newFavoriteFoods: List<Favorite>) {
        val favoriteFoodDiffUtil = FoodsDiffUtil(favoriteFoodList, newFavoriteFoods)
        val diffUtilResult = DiffUtil.calculateDiff(favoriteFoodDiffUtil)
        favoriteFoodList = newFavoriteFoods
        diffUtilResult.dispatchUpdatesTo(this)
    }
}