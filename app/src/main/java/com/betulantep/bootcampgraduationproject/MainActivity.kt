package com.betulantep.bootcampgraduationproject

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.betulantep.bootcampgraduationproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showNavigationViewControl()
    }

    private fun showNavigationViewControl() {
        binding.bottomNavView.background = null

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragmentActivityMain) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.splashFragment,
                R.id.onBoardingFragment,
                R.id.signInFragment,
                R.id.signUpFragment,
                R.id.welcomeFragment,
                R.id.homeFragment,
                R.id.userFragment,
            )
        )
        val showBottomNavigationIds = listOf(
            R.id.homeFragment,
            R.id.userFragment
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Log.e("Alt menu", destination.id.toString())
            if (showBottomNavigationIds.contains(destination.id)) {
                binding.navigationViewCoordinator.visibility = View.VISIBLE
            } else {
                binding.navigationViewCoordinator.visibility = View.GONE
            }
        }
        NavigationUI.setupWithNavController(binding.bottomNavView,navController)
    }
}