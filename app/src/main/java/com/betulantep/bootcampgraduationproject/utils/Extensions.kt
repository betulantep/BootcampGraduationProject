package com.betulantep.bootcampgraduationproject.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
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
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

fun Navigation.actionFragment(view: View,navDirections: NavDirections){
    findNavController(view).navigate(navDirections)
}
fun Navigation.actionFragment(view: View,id:Int){
    findNavController(view).navigate(id)
}

fun Activity.updateStatusBarColor(colorValue: Int) {
    window.statusBarColor = resources.getColor(colorValue)
}


fun showSnackBar(view: View, @StringRes message: Int){
    Snackbar.make(view,message, Snackbar.LENGTH_SHORT)
        .setAnimationMode(com.airbnb.lottie.R.anim.abc_fade_in)
        .show()
}

fun showToast(context: Context, @StringRes message: Int){
    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
}

