package com.example.gmergames.screens.favItemList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.gmergames.data.Item
import com.example.gmergames.dependencies.MyApplication
import com.example.gmergames.repositories.GamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavItemListVM(
    private val gamesRepository: GamesRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<FavItemListUiState> =
        MutableStateFlow(FavItemListUiState())
    val uiState: StateFlow<FavItemListUiState> = _uiState.asStateFlow()

    //TODO arreglar esto
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val myGameResp = gamesRepository.getFavGames()
            if (myGameResp != null) {
                _uiState.update { currentSate ->
                    currentSate.copy(
                        isLoading = false,
                        gameList = myGameResp
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
        }
    }

    fun gameDeletedFromFav(pos: Int) {
        _uiState.update { currentSate ->
            val games = currentSate.gameList.toMutableList()
            games.removeAt(pos)
            currentSate.copy(
                gameList = games
            )
        }
    }

    fun deleteGameFromFav(pos : Int){
        viewModelScope.launch(Dispatchers.IO){
            val gameToRemove = _uiState.value.gameList.get(pos)
            gamesRepository.deleteFavGame(gameToRemove)
            if (!gamesRepository.getFavGames()!!.contains(gameToRemove)){
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        delFromFav = true
                    )
                }
            }
            else{
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        delFromFav = false,
                        error = true
                    )
                }
            }
        }
    }

    //baja la bandera de borrado de favoritos
    fun gameDeleted() {
        _uiState.update { currenState ->
            currenState.copy(
                delFromFav = false
            )
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

                return FavItemListVM(
                    (application as MyApplication).appContainer.gamesRepository
                ) as T
            }
        }
    }
}