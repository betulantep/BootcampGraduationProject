package com.betulantep.bootcampgraduationproject.di

import android.content.Context
import com.betulantep.bootcampgraduationproject.utils.AppPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAppPref(@ApplicationContext context: Context): AppPref{
        return AppPref(context)
    }

}