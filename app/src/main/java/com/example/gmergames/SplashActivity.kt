package com.example.gmergames

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView

import androidx.appcompat.app.AppCompatActivity
import com.example.gmergames.screens.login.LoginFragment

class SplashActivity : AppCompatActivity(){

    private val SPLASH_DISPLAY_LENGTH: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Espera 3 segundos antes de navegar al LoginFragment
        Handler(Looper.getMainLooper()).postDelayed({
            supportFragmentManager.beginTransaction()
                .replace(R.id.llcSplash, LoginFragment.newInstance())
                .commit()
        }, SPLASH_DISPLAY_LENGTH)
    }
}