package com.example.gmergames.screens.itemList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.gmergames.data.Item
import com.example.gmergames.dependencies.MyApplication
import com.example.gmergames.repositories.GamesRepository
import com.example.gmergames.repositories.UserPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemListVM(
    private val gamesRepository: GamesRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel(){

    private val _uiState: MutableStateFlow<ItemListUiState> = MutableStateFlow(ItemListUiState())
    val uiState : StateFlow<ItemListUiState> = _uiState.asStateFlow()

    private var _name : String? = ""
    val name: String?
        get() = _name

    init{
        viewModelScope.launch(Dispatchers.IO) {
            val myGameResp = gamesRepository.getRandomGames(NUM_GAMES)
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

    fun getName() : String?{
        _name = userPreferencesRepository.getName().toString()
        return _name
    }

    fun deleteGame(pos: Int) {
        _uiState.update { currentSate ->
            val games = currentSate.gameList.toMutableList()
            games.removeAt(pos)
            currentSate.copy(
                gameList = games
            )
        }
    }

    //TODO cómo añado a favs?
    fun addItemToFav(pos : Int){
        viewModelScope.launch(Dispatchers.IO){
            val gameAdded = _uiState.value.gameList.get(pos)
            gamesRepository.addItemToFav(gameAdded)
            if (gamesRepository.getFavGames()!!.contains(gameAdded)){
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        addedToFav = true
                    )
                }
            }
            else{
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        addedToFav = false,
                        error = true
                    )
                }
            }
        }
    }

    //baja la bandera de añadido a favoritos
    fun gameAdded() {
        _uiState.update { currenState ->
            currenState.copy(
                addedToFav = false
            )
        }
    }


    companion object {

        const val NUM_GAMES = 20

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return ItemListVM(
                    (application as MyApplication).appContainer.gamesRepository,
                    application.appContainer.userPreferencesRepository
                ) as T
            }
        }
    }
}