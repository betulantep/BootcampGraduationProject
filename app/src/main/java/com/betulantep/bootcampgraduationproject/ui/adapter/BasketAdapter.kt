package com.betulantep.bootcampgraduationproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.data.entity.Basket
import com.betulantep.bootcampgraduationproject.databinding.RowLayoutBasketBinding
import com.betulantep.bootcampgraduationproject.ui.basket.BasketViewModel
import com.google.android.material.snackbar.Snackbar

class BasketAdapter(
    var basketList: List<Basket>,
    var viewModel: BasketViewModel
) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    var total = 0

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
        val mContext = binding.root.context
        bind(basket)
        total += viewModel.processSubTotal(basket.basket_order_quantity,basket.basket_food_price)
        binding.ivFoodDelete.setOnClickListener {
            Snackbar.make(binding.root, R.string.silmek_istediginize_emin_misiniz, Snackbar.LENGTH_LONG)
                .setAction(R.string.evet) {
                    viewModel.deleteFood(basket.basket_food_id, viewModel.username)
                    total -= viewModel.processSubTotal(basket.basket_order_quantity,basket.basket_food_price)
                }.show()
        }
        binding.subTotalResult = "₺${viewModel.processSubTotal(basket.basket_order_quantity,basket.basket_food_price)}"
        viewModel.viewModelTotal.value = total
    }

    override fun getItemCount(): Int = basketList.size


}