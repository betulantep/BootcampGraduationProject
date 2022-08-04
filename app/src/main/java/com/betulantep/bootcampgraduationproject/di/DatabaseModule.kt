package com.betulantep.bootcampgraduationproject.di

import android.content.Context
import androidx.room.Room
import com.betulantep.bootcampgraduationproject.data.database.FavoriteDatabase
import com.betulantep.bootcampgraduationproject.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, FavoriteDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDao(database: FavoriteDatabase) = database.favoriteDao()

}