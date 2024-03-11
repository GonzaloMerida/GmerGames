package com.example.gmergames.screens.detailFavItem

data class DetailFavItemUiState (
    val isLoading : Boolean = true,
    val game : Item? = null,
    val error : Boolean = false
)