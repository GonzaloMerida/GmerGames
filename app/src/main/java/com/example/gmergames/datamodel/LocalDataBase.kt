package com.example.gmergames.datamodel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gmergames.data.Game


@Database(
    entities = [Game::class],
    version = 1,
    exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun gamesDao() : GameDao

    companion object {
        @Volatile
        private var Instance: LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, LocalDatabase::class.java, "gmergames_database")
                    // Setting this option in your app's database builder means that Room
                    // permanently deletes all data from the tables in your database when it
                    // attempts to perform a migration with no defined migration path.
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}