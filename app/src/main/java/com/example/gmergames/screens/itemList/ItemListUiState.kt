package com.example.gmergames.screens.itemList

data class ItemListUiState(
    val isLoading: Boolean = true,
    val gameList: List<Item> = emptyList(),
    val error: Boolean = false
)