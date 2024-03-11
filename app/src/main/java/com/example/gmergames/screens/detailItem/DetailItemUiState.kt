package com.example.gmergames.screens.detailItem

import com.example.gmergames.data.Item

data class DetailItemUiState (
    val isLoading : Boolean = true,
    val game : Item? = null,
    val error : Boolean = false
)