package com.betulantep.bootcampgraduationproject.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.data.entity.Favorite
import com.betulantep.bootcampgraduationproject.data.entity.Food
import com.betulantep.bootcampgraduationproject.databinding.FragmentDetailBinding
import com.betulantep.bootcampgraduationproject.ui.favorite.FavoriteViewModel
import com.betulantep.bootcampgraduationproject.utils.actionFragment
import com.betulantep.bootcampgraduationproject.utils.showSnackBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding : FragmentDetailBinding
    private lateinit var navArgs: DetailFragmentArgs
    private lateinit var viewModel: DetailViewModel
    val favoriteViewModel : FavoriteViewModel by viewModels()

    var savedFavoriteFood = false
    var savedFavoriteFoodId = 0

    var quantity = 0
    var subTotal = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail, container, false)
        binding.topAppBar.setNavigationOnClickListener {
            Navigation.actionFragment(it,DetailFragmentDirections.actionDetailFragmentToHomeFragment())
        }
        val bundle : DetailFragmentArgs by navArgs()
        navArgs = bundle
        binding.food = navArgs.food
        binding.viewModel = viewModel
        binding.detailFragment = this

        viewModel.basketFoodList.observe(viewLifecycleOwner){
            for (basket in it){
                if(basket.basket_food_name == navArgs.food.foodName){
                    binding.tvDetailQuantity.text = basket.basket_order_quantity.toString()
                    subTotal = basket.basket_food_price * basket.basket_order_quantity
                    binding.tvDetailSubTotal.text = "₺$subTotal"
                }
            }
        }
        checkSavedRecipes()

        binding.ivDetailFavorite.setOnClickListener {
            if(!savedFavoriteFood){
                saveToFavorite()
            }else{
                removeFromFavorites()
            }
        }
        return binding.root
    }

    fun clickedAddToCart(food: Food,quantity: Int){
        viewModel.clickedAddToCart(food,quantity)
        showSnackBar(requireView(),R.string.urun_sepete_eklendi)
    }
    fun clickedAdd(){
        quantity = binding.tvDetailQuantity.text.toString().toInt()
        quantity++
        subTotal = quantity * navArgs.food.foodPrice
        binding.tvDetailSubTotal.text = "₺$subTotal"
        binding.tvDetailQuantity.text = quantity.toString()

    }
    fun clickedDelete(){
        quantity = binding.tvDetailQuantity.text.toString().toInt()
        if(quantity!=0){
            quantity--
            subTotal = quantity * navArgs.food.foodPrice
            binding.tvDetailSubTotal.text = "₺$subTotal"
            binding.tvDetailQuantity.text = quantity.toString()
        }
    }

    //Favorite
    private fun checkSavedRecipes() {
        favoriteViewModel.readFavoriteFood.observe(viewLifecycleOwner, Observer {
            try {
                savedFavoriteFood = false
                for(favorite in it){
                    if(favorite.username == viewModel.userName){
                        for (savedFood in it) {
                            if (savedFood.food.foodId == navArgs.food.foodId) {
                                changeColorFavoriteIcon(binding.ivDetailFavorite,R.color.red)
                                savedFavoriteFoodId = savedFood.id
                                savedFavoriteFood = true
                                break
                            }
                            changeColorFavoriteIcon(binding.ivDetailFavorite,R.color.mediumGray)
                        }
                }
                            }
            } catch (e: Exception) {
                Log.d("DetailsFragment", e.message.toString())
            }
        })
    }
    private fun saveToFavorite() {
        val favorite = Favorite(0, navArgs.food,viewModel.userName)
        favoriteViewModel.insertFavoriteFood(favorite)
        binding.ivDetailFavorite.visibility = View.GONE
        binding.lottieFavorite.visibility = View.VISIBLE
        binding.lottieFavorite.playAnimation()
        lifecycleScope.launch {
            delay(2000)
            binding.lottieFavorite.visibility = View.GONE
            binding.ivDetailFavorite.visibility = View.VISIBLE
            changeColorFavoriteIcon(binding.ivDetailFavorite,R.color.red)
        }

        showSnackBar(requireView(),R.string.favorilere_eklendi)
        savedFavoriteFood = true
    }

    private fun removeFromFavorites() {
        val favorite = Favorite(savedFavoriteFoodId,navArgs.food,viewModel.userName)
        favoriteViewModel.deleteFavoriteFood(favorite)
        changeColorFavoriteIcon(binding.ivDetailFavorite,R.color.mediumGray)
        showSnackBar(requireView(),R.string.favorilerden_silindi)
        savedFavoriteFood = false
    }
    //Favorite

    private fun changeColorFavoriteIcon(imageView: ImageView,color:Int){
        DrawableCompat.setTint(imageView.getDrawable(), ContextCompat.getColor(requireContext(),color));
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : DetailViewModel by viewModels()
        viewModel = tempViewModel
    }

}