package com.betulantep.bootcampgraduationproject.ui.bindingadapter

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.data.entity.Favorite
import com.betulantep.bootcampgraduationproject.data.entity.Food

class FoodRowBinding {
    companion object{
        @JvmStatic
        @BindingAdapter("app:load_image")
        fun loadImage(imageView: ImageView, foodImageName: String){
            val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/$foodImageName"
            imageView.load(imageUrl){
                crossfade(600)
                error(R.drawable.ic_baseline_refresh_24)
            }
        }

        @JvmStatic
        @BindingAdapter("app:favorite_food")
        fun foodFavorite(imageView: ImageView, favorite: Boolean){
            if(favorite){
                imageView.setColorFilter(
                    ContextCompat.getColor(imageView.context, R.color.red)
                )
            }

        }
    }
}