package com.example.gmergames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gmergames.screens.login.LoginFragment

class MainActivity : AppCompatActivity() {
    companion object {
        const val LIFECYCLE = "LifeCycle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            // Solo muestra el LoginFragment si la actividad se crea por primera vez
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment())
                .commit()
        }
    }
}