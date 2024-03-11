package com.example.gmergames.screens.userInfo


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.gmergames.dataStore.UserPreferences
import com.example.gmergames.dependencies.MyApplication
import com.example.gmergames.repositories.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserInfoVM(
    private val userPreferencesRepository : UserPreferencesRepository
) : ViewModel(){

    private val _uiState : MutableStateFlow<UserPreferences> = MutableStateFlow(UserPreferences())
    val uiState : StateFlow<UserPreferences> = _uiState.asStateFlow()

    init{
        viewModelScope.launch {
            updateState()
        }
    }

    private suspend fun updateState() {
        userPreferencesRepository.getUserPrefs().collect{userPreferencesFlow ->
            _uiState.update { currentState ->
                userPreferencesFlow.copy()
            }
        }
    }

    fun saveUserPrefs(name : String, showCheckBox : Boolean){
        Log.d("datastore", "nombre: $name, mostrarCheckBox: $showCheckBox")
        viewModelScope.launch {
            userPreferencesRepository.saveUserPreferences(name, showCheckBox)
            updateState()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return UserInfoVM(
                    (application as MyApplication).appContainer.userPreferencesRepository
                ) as T
            }
        }
    }
}