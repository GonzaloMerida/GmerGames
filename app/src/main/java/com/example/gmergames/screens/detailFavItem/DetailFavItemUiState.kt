package com.example.gmergames.screens.detailFavItem

import com.example.gmergames.data.Item

data class DetailFavItemUiState (
    val isLoading : Boolean = true,
    val game : Item? = null,
    val error : Boolean = false
)