package com.example.gmergames.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("item")
data class Item (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "name")
    val name : String = "",
    @ColumnInfo(name = "summary")
    val summary : String = "",
    @ColumnInfo(name = "genre")
    val genre : String = "",
    @ColumnInfo(name = "photo")
    val photo : String = "",
    @ColumnInfo(name = "rating")
    val rating : Int = 0
    )