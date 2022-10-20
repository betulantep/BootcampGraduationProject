package com.betulantep.bootcampgraduationproject.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar

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

fun ImageView.changeColorFavorite(@ColorRes color: Int){
    this.setColorFilter(ContextCompat.getColor(this.context, color))
}

