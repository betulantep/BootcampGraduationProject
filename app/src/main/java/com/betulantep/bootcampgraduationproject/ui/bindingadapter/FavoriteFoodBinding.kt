package com.betulantep.bootcampgraduationproject.ui.bindingadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.betulantep.bootcampgraduationproject.data.entity.Favorite
import com.betulantep.bootcampgraduationproject.ui.adapter.FavoriteAdapter


class FavoriteFoodBinding() {
    companion object{

        @BindingAdapter("viewVisibility", "setData", requireAll = false)
        @JvmStatic
        fun setDataAndViewVisibility(
            view: View,
            favorite: List<Favorite>?,
            mAdapter: FavoriteAdapter?
        ){
            if(favorite.isNullOrEmpty()){
                when(view){
                    is ImageView -> view.visibility = View.VISIBLE
                    is TextView -> view.visibility = View.VISIBLE
                    is RecyclerView -> view.visibility = View.INVISIBLE
                }
            }else{
                when(view){
                    is ImageView -> view.visibility = View.INVISIBLE
                    is TextView -> view.visibility = View.INVISIBLE
                    is RecyclerView -> {
                        view.visibility = View.VISIBLE
                        mAdapter?.setData(favorite)
                    }
                }
            }
        }
    }
}