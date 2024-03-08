package com.example.gmergames.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("game")
data class GameDataModel (
    @PrimaryKey(autoGenerate = true)
    private val id : Long = 0L,
    @ColumnInfo(name = "name")
    private val name : String = "",
    @ColumnInfo(name = "rating")
    private val rating : Double = 0.0,
    @ColumnInfo(name = "summary")
    private val summary : String = "",
    @ColumnInfo(name = "genre")
    private val genres : List<String> = listOf(),
    @ColumnInfo(name = "platforms")
    private val platforms : List<String> = listOf()
    )