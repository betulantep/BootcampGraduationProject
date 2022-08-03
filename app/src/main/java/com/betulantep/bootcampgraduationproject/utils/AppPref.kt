package com.betulantep.bootcampgraduationproject.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AppPref @Inject constructor(var context: Context) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore("information")
    companion object {
        val KEY_SHOULD_SHOW_ON_BOARDING = booleanPreferencesKey("SHOULD_SHOW_ON_BOARDING")
        val USERNAME = stringPreferencesKey("USERNAME")
    }

    suspend fun putUsername(username: String) {
        context.dataStore.edit {
            it[USERNAME] = username
        }
    }

    suspend fun getUsername(): String {
        val p = context.dataStore.data.first()
        return p[USERNAME] ?: "betul"
    }
    suspend fun putOnBoardingShow(show: Boolean) {
        context.dataStore.edit {
            it[KEY_SHOULD_SHOW_ON_BOARDING] = show
        }
    }

    suspend fun getOnBoardingShow(): Boolean {
        val p = context.dataStore.data.first()
        return p[KEY_SHOULD_SHOW_ON_BOARDING] ?: true
    }
}