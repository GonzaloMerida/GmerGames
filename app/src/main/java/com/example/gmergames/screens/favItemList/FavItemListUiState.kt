package com.example.gmergames.screens.favItemList

class FavItemListUiState (
    val isLoading: Boolean = true,
    val gameList: List<Item> = emptyList(),
    val error: Boolean = false
)