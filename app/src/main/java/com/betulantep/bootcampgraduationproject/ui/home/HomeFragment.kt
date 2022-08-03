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
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.databinding.FragmentHomeBinding
import com.betulantep.bootcampgraduationproject.ui.adapter.FoodAdapter
import com.betulantep.bootcampgraduationproject.utils.RecyclerItemDecoration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        auth = Firebase.auth
        viewModel.putUsername(auth.currentUser!!.email.toString())

        Log.e("username", auth.currentUser!!.email.toString())
        showShimmerEffect()
        observeLiveData()
        searchFood()

        binding.rvFoodHome.addItemDecoration(RecyclerItemDecoration())

        return binding.root
    }

    private fun observeLiveData(){
        viewModel.foodsList.observe(viewLifecycleOwner){
            val adapter = FoodAdapter(it)
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