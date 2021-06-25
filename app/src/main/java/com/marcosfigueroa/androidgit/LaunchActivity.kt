package com.marcosfigueroa.androidgit

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LaunchActivity : AppCompatActivity() {

    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        sp = getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val sesion = sp.getString("sesion", "")

        if (sesion != "1") {
            // Iniciar sesion
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            // Sesion iniciada
            startActivity(Intent(this, InicioActivity::class.java))
        }

    }
}