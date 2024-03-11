package com.example.gmergames.screens.detailItem

data class DetailItemUiState (
    val isLoading : Boolean = true,
    val game : Item? = null,
    val error : Boolean = false
)