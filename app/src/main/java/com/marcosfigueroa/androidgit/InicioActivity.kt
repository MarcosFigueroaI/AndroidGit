package com.marcosfigueroa.androidgit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_inicio.*

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        //Recuperar intent
        val usuario = intent.getStringExtra("usuario")

        // TextView
        tvUsuario.text = "Bienvenido $usuario"

        //Boton Salir
        btnSalir.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }
}