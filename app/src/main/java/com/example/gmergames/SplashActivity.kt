package com.example.gmergames

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.os.Handler
import android.widget.ImageView

import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity(){

    private val SPLASH_DISPLAY_LENGTH: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val imageView = findViewById<ImageView>(R.id.ivLoadImage)

        imageView.postDelayed({
            val mainIntent = Intent(this@SplashActivity, LoginFragment::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}