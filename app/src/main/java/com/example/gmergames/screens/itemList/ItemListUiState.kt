package com.example.gmergames.screens.itemList

import com.example.gmergames.data.Game
import com.example.gmergames.data.Item

data class ItemListUiState(
    val isLoading: Boolean = true,
    val gameList: List<Item> = emptyList(),
    val error: Boolean = false
)