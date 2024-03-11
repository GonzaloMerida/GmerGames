package com.example.gmergames.screens.favItemList

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

class FavItemListVM(
    private val gamesRepository: GamesRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<FavItemListUiState> = MutableStateFlow(FavItemListUiState())
    val uiState: StateFlow<FavItemListUiState> = _uiState.asStateFlow()

    //TODO arreglar esto
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val myGameResp = gamesRepository.getFavGames()
            if (myGameResp.isSuccessful) {
                val myGames = myGameResp.body()
                _uiState.update { currentSate ->
                    currentSate.copy(
                        isLoading = false,
                        gameList = (myGames?.let { it.toList() } ?: emptyList<Item>()) as List<Item>
                    )
                }
            } else {
                //error en la respuesta...
                _uiState.update { currentSate ->
                    currentSate.copy(
                        isLoading = false,
                        error = true
                    )
                }
            }
        }
    }



    fun delItemFromFav(pos: Int) {
//        _uiState.update { currentSate ->
//            val games = currentSate.itemList.toMutableList()
//            games.removeAt(pos)
//            currentSate.copy(
//                gameList = games
//            )
//        }
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