package com.example.gmergames.screens.favItemList

import com.example.gmergames.data.Item

data class FavItemListUiState (
    val isLoading: Boolean = true,
    val gameList: List<Item> = emptyList(),
    val error: Boolean = false,
    val delFromFav: Boolean = false
)