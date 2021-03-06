package com.marcosfigueroa.androidgit.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.marcosfigueroa.androidgit.ui.MainActivity
import com.marcosfigueroa.androidgit.R

class LaunchActivity : AppCompatActivity() {

    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        // No dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

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