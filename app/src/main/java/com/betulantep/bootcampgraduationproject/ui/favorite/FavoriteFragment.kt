package com.betulantep.bootcampgraduationproject.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.databinding.FragmentFavoriteBinding
import com.betulantep.bootcampgraduationproject.ui.adapter.FavoriteAdapter
import com.betulantep.bootcampgraduationproject.utils.RecyclerItemDecoration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var auth: FirebaseAuth
    lateinit var userName : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_favorite, container, false)
        auth = Firebase.auth
        userName = auth.currentUser!!.email.toString()

        viewModel.readFavoriteFood.observe(viewLifecycleOwner){
            for (favorite in it){
                if(favorite.username == userName){
                    binding.mAdapter = FavoriteAdapter(it,viewModel)
                }
            }
            if(it.isNullOrEmpty()){
                binding.ivNoFood.visibility = View.VISIBLE
                binding.tvNoFood.visibility = View.VISIBLE
                binding.rvFavoriteFood.visibility = View.GONE
            }else{
                binding.ivNoFood.visibility = View.GONE
                binding.tvNoFood.visibility = View.GONE
            }
        }

        binding.rvFavoriteFood.addItemDecoration(RecyclerItemDecoration())

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : FavoriteViewModel by viewModels()
        viewModel = tempViewModel
    }
}