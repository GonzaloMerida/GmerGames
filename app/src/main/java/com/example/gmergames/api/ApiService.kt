package com.example.gmergames.api

import com.example.gmergames.data.Game
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST
    suspend fun getGame(@Path("id")id: Int) : Response<Game>


}