package com.example.gmergames.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.gmergames.dependencies.MyApplication
import com.example.gmergames.repositories.GamesRepository
import com.example.gmergames.repositories.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginVM(
    private val gamesRepository: GamesRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val uiState : StateFlow<LoginUiState> = _uiState.asStateFlow()

    private var _name : String? = ""
    val name: String?
        get() = _name

    init {
        viewModelScope.launch {
            userPreferencesRepository.getUserPrefs().collect { userPreferences ->
                _uiState.update {
                    it.copy(
                        name = userPreferences.name,
                        checkBox = userPreferences.showCheckBox
                    )
                }
            }
        }
    }

    fun getName() : String?{
        _name = userPreferencesRepository.getName().toString()
        return _name
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return LoginVM(
                    (application as MyApplication).appContainer.gamesRepository,
                    application.appContainer.userPreferencesRepository
                ) as T
            }
        }
    }
}