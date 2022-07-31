package com.betulantep.bootcampgraduationproject.ui.bindingadapter

import android.util.Patterns
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:textChangedEmailListener")
fun textChangedEmailListener(et: EditText, textInputLayout: TextInputLayout){
    et.setOnFocusChangeListener { _, focused ->
        if(!focused){
            textInputLayout.helperText = validEditTextEmail(et)
        }
    }
}

fun validEditTextEmail(et: EditText): String? {
    if(!Patterns.EMAIL_ADDRESS.matcher(et.text.toString()).matches()){
        return "Invalid Email Address"
    }
    return null
}

@BindingAdapter("app:textChangedPasswordListener")
fun textChangedPasswordListener(et: EditText, textInputLayout: TextInputLayout){
    et.setOnFocusChangeListener { _, focused ->
        if(!focused){
            textInputLayout.helperText = validEditTextPassword(et.text.toString())
        }
    }
}

fun validEditTextPassword(password:String): String? {
    if(password.length < 8){
        return "Minimum 8 Character Password"
    }
    if(!password.matches(".*[A-Z].*".toRegex())){
        return "Must Contain 1 Upper-case Character"
    }
    if(!password.matches(".*[a-z].*".toRegex())){
        return "Must Contain 1 Lower-case Character"
    }
    if(!password.matches(".*[@#\$%^&+=*].*".toRegex())){
        return "Must Contain 1 Special Character (@#\$%^&+=*)"
    }
    return null
}