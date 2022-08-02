package com.betulantep.bootcampgraduationproject.data.entity

import java.io.Serializable

data class Quantity(
    var food_id:Int,
    var quantity:Int,
    var subtotal_price:Int
): Serializable{}
