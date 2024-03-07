package com.example.gmergames.screens.item

//TIene que ir en datasource con las anotaciones de @Entity, @PrimaryKey, @ColumnInfo
data class Item(
    val name : String ="",
    val company : String = "",
    val description : String = "",
    val photo : String = "",
)
