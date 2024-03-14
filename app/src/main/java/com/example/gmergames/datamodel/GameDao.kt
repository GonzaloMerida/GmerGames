package com.example.gmergames.datamodel

import androidx.room.*
import androidx.room.OnConflictStrategy
import com.example.gmergames.data.Item
import kotlinx.coroutines.flow.Flow


@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertGame(item : Item)

    @Delete
    fun deleteGame(item : Item)

    @Query("SELECT * FROM item ORDER BY id")
    fun getAllGames() : List<Item>?

    @Query("SELECT * FROM item ORDER BY RANDOM() DESC LIMIT 1")
    fun getRandomGame() : Item?

    @Query("DELETE FROM item")
    fun clearGames()

    @Query("SELECT * FROM item WHERE GENRE = :userGenre")
    fun getGamesByGenre(userGenre : String) : List<Item>

}