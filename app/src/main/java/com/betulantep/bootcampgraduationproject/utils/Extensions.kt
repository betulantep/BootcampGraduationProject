package com.betulantep.bootcampgraduationproject.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun Navigation.actionFragment(view: View,navDirections: NavDirections){
    findNavController(view).navigate(navDirections)
}