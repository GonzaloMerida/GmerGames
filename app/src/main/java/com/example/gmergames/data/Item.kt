package com.example.gmergames.data

//TIene que ir en datasource con las anotaciones de @Entity, @PrimaryKey, @ColumnInfo
data class Item(
    val id : Int,
    val name : String,
    val summary : String,
    val genre : String,
    val photo : String
)
