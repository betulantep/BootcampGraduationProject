package com.betulantep.bootcampgraduationproject.data.database

import androidx.room.*
import com.betulantep.bootcampgraduationproject.data.entity.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteFood(favorite: Favorite)

    @Query("SELECT * FROM favorite_food_table ORDER BY id ASC")
    fun readFavoriteFood(): Flow<List<Favorite>>

    @Delete
    suspend fun deleteFavoriteFood(favorite: Favorite)

    @Query("DELETE FROM favorite_food_table")
    suspend fun deleteAllFavoriteFoods()
}
