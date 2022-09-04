package com.betulantep.bootcampgraduationproject.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.data.entity.Favorite
import com.betulantep.bootcampgraduationproject.data.entity.Food
import com.betulantep.bootcampgraduationproject.databinding.FragmentHomeBinding
import com.betulantep.bootcampgraduationproject.ui.adapter.FoodAdapter
import com.betulantep.bootcampgraduationproject.ui.favorite.FavoriteViewModel
import com.betulantep.bootcampgraduationproject.utils.RecyclerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private var favoriteList : List<Favorite> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        showShimmerEffect()
        observeLiveData()
        searchFood()

        binding.rvFoodHome.addItemDecoration(RecyclerItemDecoration())

        return binding.root
    }

    private fun observeLiveData(){
        favoriteViewModel.readFavoriteFood.observe(viewLifecycleOwner){
            favoriteList = it
        }
        viewModel.foodsList.observe(viewLifecycleOwner){
            val adapter = FoodAdapter(it,favoriteList,favoriteViewModel)
            binding.foodAdapter = adapter
        }
        viewModel.foodLoading.observe(viewLifecycleOwner){
            if(it){
                showShimmerEffect()
            }else{
                hideShimmerEffect()
            }
        }
    }

    private fun searchFood(){
        binding.etSearchHome.setOnFocusChangeListener { _, focused ->
            if(focused){
                binding.etSearchHome.addTextChangedListener(object: TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        viewModel.searchFood(p0.toString())
                        /*var list = viewModel.foodsList.value
                        var newList = ArrayList<Food>()
                        list!!.forEach {
                            if(it.foodName.contains(p0.toString())){
                                newList.add(it)
                            }
                            binding.foodAdapter = FoodAdapter(newList)
                        }*/
                    }
                    override fun afterTextChanged(p0: Editable?) {}
                })
            }
        }
    }

    private fun showShimmerEffect() = with(binding) {
        shimmerFrameLayout.visibility = View.VISIBLE
        shimmerFrameLayout.startShimmer()
        rvFoodHome.visibility = View.GONE
    }

    private fun hideShimmerEffect() = with(binding) {
        shimmerFrameLayout.stopShimmer()
        shimmerFrameLayout.visibility = View.GONE
        rvFoodHome.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : HomeViewModel by viewModels()
        viewModel = tempViewModel

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.putOnBoarding()
    }

}