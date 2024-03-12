package com.example.gmergames.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.gmergames.dataStore.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesRepository (
    private val userDataStore : DataStore<Preferences>
) {

    //Tomar las preferencias del usuario del dataStore
    fun getUserPrefs() : Flow<UserPreferences> {
        return userDataStore.data.map { userPreferences ->
            val name = userPreferences[stringPreferencesKey(UserPreferences.USER_NAME)]
                ?: UserPreferences.DEFATULT_NAME
            val showCheckBox = userPreferences[booleanPreferencesKey(UserPreferences.SHOW_CHECKBOX.toString())]
                ?: true
            return@map UserPreferences(
                name = name,
                showCheckBox = showCheckBox
            )
        }
    }

    //Guardar las preferencias del usuario en el dataStore
    suspend fun saveUserPreferences(name : String, showCheckBox : Boolean){
        userDataStore.edit { userPreferences ->
            userPreferences[stringPreferencesKey(UserPreferences.USER_NAME)] = name
            userPreferences[booleanPreferencesKey(UserPreferences.SHOW_CHECKBOX.toString())] = showCheckBox
        }
    }

    //Obtener el nombre almacenado en el DataStore
    fun getName(): Flow<String?> {
        return userDataStore.data.map { userPreferences ->
            userPreferences[stringPreferencesKey(UserPreferences.USER_NAME)]
        }
    }
}