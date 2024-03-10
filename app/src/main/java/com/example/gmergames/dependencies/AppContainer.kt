package com.example.gmergames.dependencies

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.example.gmergames.api.ApiService
import com.example.gmergames.datamodel.LocalDatabase
import com.example.gmergames.repositories.GamesRepository
import com.example.myapplication.api.GameApiConfig


//Datastore. Configuración básica de la app.
//val Context.userDataStore by preferencesDataStore(name = UserPreferences.SETTINGS_FILE)


class AppContainer(context : Context) {
    //Api Retrofit2
    private val gameApiService = GameApiConfig.provideRetrofit().create(ApiService::class.java)

    //Repositorio de juegos.
     val gamesRepository : GamesRepository = GamesRepository(gameApiService)
}