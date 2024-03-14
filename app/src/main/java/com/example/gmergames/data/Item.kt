package com.example.gmergames.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("item")
data class Item (
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    @ColumnInfo(name = "name")
    var name : String = "",
    @ColumnInfo(name = "summary")
    var summary : String = "",
    @ColumnInfo(name = "genre")
    var genre : String = "",
    @ColumnInfo(name = "photo")
    var photo : String = "",
    @ColumnInfo(name = "rating")
    var rating : Int = 0
)