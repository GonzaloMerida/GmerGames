package com.example.gmergames

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreditActivity : AppCompatActivity() {
    private lateinit var tvInfo: TextView
    private lateinit var btnContact: Button

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit)

        tvInfo = findViewById<TextView>(R.id.tvInfo)
        btnContact = findViewById<Button>(R.id.btnContact)

        val userName = intent.getStringExtra(MainActivity.enteredUserName)
        tvInfo.text = getString(R.string.infoVersion, userName)

        btnContact.setOnClickListener {
            val intent2 = Intent(Intent.ACTION_SEND)
            intent2.type = "text/plain"
            intent2.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.email_destiny)))
            intent2.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
            intent2.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body))

            //si hay una actividad disponible que pueda usar el intent, se lanza e inicia la app de
            //turno que pueda mandar e-mails
            if (intent2.resolveActivity(packageManager) != null) {
                startActivity(intent2)
            }
            //si no existe instalada ninguna app capaz de mandar e-mails, se notifica al usuario de esto.
            else {
                Toast.makeText(this, getString(R.string.no_email_apps), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
