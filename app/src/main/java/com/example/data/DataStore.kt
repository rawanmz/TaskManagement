package com.example.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.taskmanagement.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStore @Inject constructor(context: Context) {

    private val onBoardingScreenKey = booleanPreferencesKey(name = PREFERENCES_NAME)

    private val dataStore = context.dataStore

    suspend fun saveOnBoardingState(showTipsPage: Boolean) {
        dataStore.edit { preferences ->
            preferences[onBoardingScreenKey] = showTipsPage
        }
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .map { preferences ->
              preferences[onBoardingScreenKey] ?: false
            }
    }
}