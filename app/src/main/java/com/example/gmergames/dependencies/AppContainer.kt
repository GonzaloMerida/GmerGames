package com.example.gmergames.dependencies

import android.content.Context
import com.example.gmergames.api.ApiService
import com.example.myapplication.api.GameApiConfig

class AppContainer(context : Context) {
    private val gameApiService = GameApiConfig.provideRetrofit().create(ApiService::class.java)


}