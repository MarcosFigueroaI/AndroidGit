package com.marcosfigueroa.androidgit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Boton acceder
        btnAcceder.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val contraseña = etContraseña.text.toString()

            // Funcion validar
            validar(usuario, contraseña)
        }

    }

    private fun validar(usuario: String, contraseña: String) {
        if (usuario.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(applicationContext, "Por favor llena todos los campos.", Toast.LENGTH_LONG).show()
        } else {
            if (usuario == "Marcos" || usuario == "Josue") {
                val intent = Intent(this, InicioActivity::class.java).apply {
                    putExtra("usuario", usuario)
                }
                startActivity(intent)
            }
        }
    }

}