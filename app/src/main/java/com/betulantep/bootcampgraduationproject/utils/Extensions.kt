package com.betulantep.bootcampgraduationproject.utils

import android.app.Activity
import android.os.Build
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.betulantep.bootcampgraduationproject.R
import com.google.android.material.textfield.TextInputLayout

fun Navigation.actionFragment(view: View,navDirections: NavDirections){
    findNavController(view).navigate(navDirections)
}

fun Activity.updateStatusBarColor(colorValue: Int) {
    window.statusBarColor = resources.getColor(colorValue)
}


