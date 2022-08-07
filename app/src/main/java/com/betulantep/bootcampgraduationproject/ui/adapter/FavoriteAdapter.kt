package com.betulantep.bootcampgraduationproject.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.betulantep.bootcampgraduationproject.data.entity.Favorite
import com.betulantep.bootcampgraduationproject.databinding.FavoriteRowLayoutBinding
import com.betulantep.bootcampgraduationproject.ui.favorite.FavoriteViewModel

class FavoriteAdapter(
    var favoriteFoodList : List<Favorite>,var viewModel:FavoriteViewModel
    ): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private var selectedFood = arrayListOf<Favorite>()
    class FavoriteViewHolder(var binding: FavoriteRowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = FavoriteRowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int)= with(holder) {
        val currentFood = favoriteFoodList[position]
        binding.favorite = currentFood
        binding.executePendingBindings()

        binding.ivFavoriteDelete.setOnClickListener {
            viewModel.deleteFavoriteFood(currentFood)
        }
    }

    override fun getItemCount(): Int = favoriteFoodList.size

}