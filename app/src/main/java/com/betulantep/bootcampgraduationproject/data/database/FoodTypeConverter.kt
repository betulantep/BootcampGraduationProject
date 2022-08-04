package com.betulantep.bootcampgraduationproject.data.database

import androidx.room.TypeConverter
import com.betulantep.bootcampgraduationproject.data.entity.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class FoodTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun resultToString(food: Food): String{
        return gson.toJson(food)
    }

    @TypeConverter
    fun stringToResult(data: String): Food{
        val listType = object : TypeToken<Food>() {}.type
        return gson.fromJson(data,listType)
    }
}