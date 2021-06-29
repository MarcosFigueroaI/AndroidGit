package com.marcosfigueroa.androidgit.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.marcosfigueroa.androidgit.R
import com.marcosfigueroa.androidgit.model.Usuario
import com.marcosfigueroa.androidgit.repository.Repository
import com.marcosfigueroa.androidgit.utils.Alertas
import com.marcosfigueroa.androidgit.viewmodel.MainViewModel
import com.marcosfigueroa.androidgit.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sp: SharedPreferences
    private lateinit var viewModel: MainViewModel
    val alerta = Alertas()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Boton acceder
        btnAcceder.setOnClickListener {
            // Variables
            val usuario = etUsuario.text.toString()
            val contraseña = etContraseña.text.toString()
            // Funcion validar
            validar(usuario, contraseña)
        }
    }

    private fun validar(usuario: String, contraseña: String) {
        if (usuario.isEmpty() || contraseña.isEmpty()) {
            alerta.mostrarAlerta(this, "Advertencia", "Por favor llena todos los campos.")
        } else {
            // mostrarProgress
            mostrarProgress()
            // repository, viewModel, observer
            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
            viewModel.login(usuario, contraseña)
            viewModel.myResponse.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    val success = response.body()?.success
                    if (success!!) {
                        // Iniciar sesion
                        sp = getSharedPreferences("sesion", Context.MODE_PRIVATE)
                        var editor = sp.edit()
                        editor.putString("sesion", "1")
                        editor.apply()
                        // Obtener nombre del usuario
                        val data = response.body()?.data
                        val token = response.body()?.token
                        val user: ArrayList<Usuario>? = data
                        for (i in user!!) {
                            sp = getSharedPreferences("usuario", Context.MODE_PRIVATE)
                            var editor = sp.edit()
                            editor.putString("nombre", i.nombre)
                            editor.putString("token", token)
                            editor.apply()
                        }
                        // ocultarProgress
                        ocultarProgress()
                        // Ir a otra actividad
                        startActivity(Intent(this, InicioActivity::class.java))
                        finish()
                    } else {
                        // ocultarProgress
                        ocultarProgress()
                        // Success False
                        val msg = response.body()?.msg
                        alerta.mostrarAlerta(this, "Advertencia", msg!!)
                    }
                } else {
                    // ocultarProgress
                    ocultarProgress()
                    // Alerta Internet
                    alerta.mostrarAlerta(this, "Error", "Ocurrio un error de conexion.")
                }
            })
        }
    }

    private fun mostrarProgress() {
        progressBar.visibility = View.VISIBLE
    }

    private fun ocultarProgress() {
        progressBar.visibility = View.GONE
    }

}