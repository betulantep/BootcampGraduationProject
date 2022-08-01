package com.betulantep.bootcampgraduationproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.betulantep.bootcampgraduationproject.databinding.ActivityMainBinding
import com.betulantep.bootcampgraduationproject.utils.updateStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showNavigationViewAndStatusBarColorControl()
    }

    private fun showNavigationViewAndStatusBarColorControl() {
        binding.bottomNavView.background = null

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragmentActivityMain) as NavHostFragment
        val navController = navHostFragment.navController

        val showBottomNavigationIds = listOf(
            R.id.homeFragment,
            R.id.userFragment
        )

        val colorPrimaryStatusBarIds = listOf(
            R.id.splashFragment,
            R.id.onBoardingFragment,
            R.id.signInFragment,
            R.id.signUpFragment
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (showBottomNavigationIds.contains(destination.id)) {
                binding.navigationViewCoordinator.visibility = View.VISIBLE
            } else {
                binding.navigationViewCoordinator.visibility = View.GONE
            }

            if (colorPrimaryStatusBarIds.contains(destination.id)) {
                updateStatusBarColor(R.color.primary)
            } else {
                updateStatusBarColor(R.color.primaryDark)
            }
        }
        NavigationUI.setupWithNavController(binding.bottomNavView, navController)
    }
}