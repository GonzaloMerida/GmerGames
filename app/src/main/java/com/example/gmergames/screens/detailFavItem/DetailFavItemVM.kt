package com.example.gmergames.screens.detailFavItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.gmergames.dependencies.MyApplication
import com.example.gmergames.repositories.GamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailFavItemVM(
    private val gamesRepository: GamesRepository
) : ViewModel() {

    private var _id : Int = 0
    val id: Int
        get() = _id

    private val _uiState: MutableStateFlow<DetailFavItemUiState> =
        MutableStateFlow(DetailFavItemUiState())
    val uiState: StateFlow<DetailFavItemUiState> = _uiState.asStateFlow()

    fun setId (id : Int){
        _id = id
    }

    fun setGame() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val gameResp = gamesRepository.getGame(id)
                if (gameResp.isSuccessful) {
                    val game = gameResp.body()
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            game = game?.toItem()
                        )

                    }
                } else {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            error = true
                        )
                    }
                }

            } catch (e: Error) {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        error = true
                    )
                }

            }
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
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return DetailFavItemVM(
                    (application as MyApplication).appContainer.gamesRepository
                ) as T
            }
        }
    }
}