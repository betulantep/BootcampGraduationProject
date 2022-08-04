package com.betulantep.bootcampgraduationproject.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.betulantep.bootcampgraduationproject.utils.Constants.FAVORITE_FOOD_TABLE
import java.io.Serializable

@Entity(tableName = FAVORITE_FOOD_TABLE)
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var food: Food
)
