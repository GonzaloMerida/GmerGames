package com.example.gmergames.api

import com.example.gmergames.data.Game
import com.example.gmergames.data.Genre
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("games")
    suspend fun getGames(
        @Query("fields") fields: String
    ): Response<List<Game>>

    @POST("games")
    suspend fun getGamesByName(
        @Query("search") gameName: String,
        @Query("fields") fields: String
    ): Response<List<Game>>

    @POST("games")
    suspend fun getGameById(
        @Query("search") id: Int,
        @Query("fields") fields: String
    ): Response<Game>

    @POST("games")
    suspend fun getGame(
        @Query("fields") fields: String
    ): Response<Game>

    @POST("genres")
    suspend fun getGenreByGameName(
        @Query("search") gameName: String,
        @Query("fields") fields: String
    ): Response<String>

    @POST("genres")
    suspend fun getGenres(
        @Query("fields") fields: String
    ): Response<String>

}