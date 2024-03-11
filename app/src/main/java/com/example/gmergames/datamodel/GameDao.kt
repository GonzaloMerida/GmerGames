package com.example.gmergames.datamodel

import androidx.room.*
import androidx.room.OnConflictStrategy
import com.example.gmergames.data.Game
import com.example.gmergames.data.Item

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertGame(item : Item)

    @Delete
    suspend fun deleteGame(item : Item)

    @Query("SELECT * FROM game ORDER BY RANDOM()")
    suspend fun getAllGames() : List<Item>?

    @Query("SELECT * FROM game ORDER BY RANDOM() DESC LIMIT 1")
    suspend fun getRandomGame() : Item?

    @Query("DELETE FROM game")
    suspend fun clearGames()

    @Query("SELECT * FROM game WHERE GENRE = :userGenre")
    fun getGamesByGenre(userGenre : String) : List<Item>

    @Query("SELECT * FROM game WHERE PLATFORMS = :userPlatform")
    fun getGamesByRating(userPlatform: String) : List<Item>
}