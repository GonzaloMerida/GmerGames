package com.example.gmergames.dependencies

import android.app.Application

class MyApplication : Application() {

    //contenedor de dependencias manuales.
    private lateinit var _appContainer : AppContainer
    val appContainer get() = _appContainer

    //Inicialización del container para que pueda recibir correctamente el contexto.
    override fun onCreate() {
        super.onCreate()
        _appContainer = AppContainer(this)
    }
}