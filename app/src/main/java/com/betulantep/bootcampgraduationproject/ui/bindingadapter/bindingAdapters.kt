package com.betulantep.bootcampgraduationproject.ui.bindingadapter

import android.util.Patterns
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:load_image")
fun loadImage(imageView: ImageView,foodImageName: String){
    val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/$foodImageName"
    imageView.load(imageUrl)
}

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
        return "Geçersiz E-posta Adresi"
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
        return "En az 8 Karakter Olmalı"
    }
    if(!password.matches(".*[A-Z].*".toRegex())){
        return "En az 1 Büyük Harf İçermeli"
    }
    if(!password.matches(".*[a-z].*".toRegex())){
        return "En az 1 Küçük Harf İçermeli"
    }
    if(!password.matches(".*[@#\$%^&+=*].*".toRegex())){
        return "En az 1 Özel Karakter İçermeli"
    }
    return null
}