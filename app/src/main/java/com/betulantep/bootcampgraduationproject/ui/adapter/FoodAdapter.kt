package com.betulantep.bootcampgraduationproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.betulantep.bootcampgraduationproject.databinding.FoodRowLayoutBinding

class FoodAdapter(var foodList: List<String>): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    class FoodViewHolder(var binding: FoodRowLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = FoodRowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) = with(holder) {
        val food = foodList[position]
        val mContext = binding.root.context
    }

    override fun getItemCount(): Int = foodList.size
}