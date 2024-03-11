package com.example.gmergames.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("game")
data class Item (
    @PrimaryKey(autoGenerate = true)
    private val id : Int,
    @ColumnInfo(name = "name")
    private val name : String,
    @ColumnInfo(name = "summary")
    private val summary : String,
    @ColumnInfo(name = "genre")
    private val genre : List<String> = listOf(),
    @ColumnInfo(name = "photo")
    private val photo : String,
    @ColumnInfo(name = "platforms")
    private val platform : List<String> = listOf()
    )