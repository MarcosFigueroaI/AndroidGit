package com.marcosfigueroa.androidgit

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_inicio.*

class InicioActivity : AppCompatActivity() {

    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        // No dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Hide action bar
        supportActionBar?.hide()

        // Obtener nombrede usuario
        sp = getSharedPreferences("usuario", Context.MODE_PRIVATE)
        val usuario = sp.getString("nombre", "")

        // TextView
        tvUsuario.text = "Bienvenido $usuario"

        //Boton Salir
        btnSalir.setOnClickListener {
            // Cerrar sesion
            sp = getSharedPreferences("sesion", Context.MODE_PRIVATE)
            var editor = sp.edit()
            editor.putString("sesion", "0")
            editor.apply()

            // Ir a otra actividad
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }
}