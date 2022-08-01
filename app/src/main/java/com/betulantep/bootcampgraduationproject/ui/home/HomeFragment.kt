package com.betulantep.bootcampgraduationproject.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.databinding.FragmentHomeBinding
import com.betulantep.bootcampgraduationproject.ui.adapter.FoodAdapter
import com.betulantep.bootcampgraduationproject.utils.RecyclerItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        initView()
        return binding.root
    }
    private fun initView(){
        observeFoodList()
        searchFood()
        binding.rvFoodHome.addItemDecoration(RecyclerItemDecoration())
    }

    private fun observeFoodList(){
        viewModel.foodsList.observe(viewLifecycleOwner){
            val adapter = FoodAdapter(it)
            binding.foodAdapter = adapter
        }
    }

    private fun searchFood(){
        binding.etSearchHome.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.searchFood(p0.toString())
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

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