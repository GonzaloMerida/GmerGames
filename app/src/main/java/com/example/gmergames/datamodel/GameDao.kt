package com.example.gmergames.datamodel

import androidx.room.*
import androidx.room.OnConflictStrategy
import com.example.gmergames.data.Game

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertGame(game : Game)

    @Delete
    suspend fun deleteGame(game : Game)

    @Query("SELECT * FROM game ORDER BY RANDOM()")
    suspend fun getAllGames() : List<Game>?

    @Query("SELECT * FROM game ORDER BY RANDOM() DESC LIMIT 1")
    suspend fun getRandomGame() : Game?

    @Query("DELETE FROM game")
    suspend fun clearGames()

    @Query("SELECT * FROM game WHERE GENRE = :userGenre")
    fun getGamesByGenre(userGenre : String) : List<Game>

    @Query("SELECT * FROM game WHERE PLATFORMS = :userPlatform")
    fun getGamesByRating(userPlatform: String) : List<Game>
}