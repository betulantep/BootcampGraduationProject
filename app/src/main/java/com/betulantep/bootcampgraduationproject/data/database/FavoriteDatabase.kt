package com.betulantep.bootcampgraduationproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.betulantep.bootcampgraduationproject.data.entity.Favorite


@Database(entities = [Favorite::class], version = 1, exportSchema = false)
@TypeConverters(FoodTypeConverter::class)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}