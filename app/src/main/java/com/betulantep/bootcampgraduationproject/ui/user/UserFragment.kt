package com.betulantep.bootcampgraduationproject.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.betulantep.bootcampgraduationproject.R
import com.betulantep.bootcampgraduationproject.databinding.FragmentHomeBinding
import com.betulantep.bootcampgraduationproject.databinding.FragmentUserBinding
import com.betulantep.bootcampgraduationproject.ui.home.HomeFragmentDirections
import com.betulantep.bootcampgraduationproject.utils.actionFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserFragment : Fragment() {
    private lateinit var binding : FragmentUserBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(layoutInflater)

        binding.buttonCikisYap.setOnClickListener {
            auth.signOut()
            Navigation.actionFragment(it,UserFragmentDirections.actionUserFragmentToSignInFragment())
        }
        return binding.root
        //return inflater.inflate(R.layout.fragment_user, container, false)
    }
}