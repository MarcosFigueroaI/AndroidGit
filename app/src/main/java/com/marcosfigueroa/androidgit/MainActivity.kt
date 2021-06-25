package com.marcosfigueroa.androidgit

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.marcosfigueroa.androidgit.model.User
import com.marcosfigueroa.androidgit.model.Usuario
import com.marcosfigueroa.androidgit.model.Usuarios
import com.marcosfigueroa.androidgit.repository.Repository
import com.marcosfigueroa.androidgit.viewmodel.MainViewModel
import com.marcosfigueroa.androidgit.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sp: SharedPreferences
    private lateinit var viewModel: MainViewModel

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
            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
            viewModel.login(usuario, contraseña)
            viewModel.myResponse.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    // Iniciar sesion
                    sp = getSharedPreferences("sesion", Context.MODE_PRIVATE)
                    var editor = sp.edit()
                    editor.putString("sesion", "1")
                    editor.apply()

                    // Obtener nombre del usuario
                    val data = response.body()?.Usuarios
                    val user: ArrayList<Usuario>? = data
                    for (i in user!!) {
                        sp = getSharedPreferences("usuario", Context.MODE_PRIVATE)
                        var editor = sp.edit()
                        editor.putString("nombre", i.nombre)
                        editor.apply()
                    }

                    // Ir a otra actividad
                    startActivity(Intent(this, InicioActivity::class.java))
                    finish()
                } else {
                    // Alerta
                }
            })
        }
    }

}