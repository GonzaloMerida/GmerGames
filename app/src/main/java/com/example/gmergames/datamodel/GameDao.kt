package com.example.gmergames.datamodel

import androidx.room.*
import androidx.room.OnConflictStrategy
import com.example.gmergames.data.Item
import kotlinx.coroutines.flow.Flow


@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    //suspend
    fun insertGame(item : Item)

    @Delete
    //suspend
    fun deleteGame(item : Item)

    @Query("SELECT * FROM item ORDER BY id")
    //suspend
    fun getAllGames() : List<Item>?

    @Query("SELECT * FROM item ORDER BY RANDOM() DESC LIMIT 1")
    //suspend
    fun getRandomGame() : Item?

    @Query("DELETE FROM item")
    //suspend
    fun clearGames()

    @Query("SELECT * FROM item WHERE GENRE = :userGenre")
    //suspend
    fun getGamesByGenre(userGenre : String) : List<Item>

}