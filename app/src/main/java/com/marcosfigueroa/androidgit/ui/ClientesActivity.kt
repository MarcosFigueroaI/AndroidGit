package com.marcosfigueroa.androidgit.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcosfigueroa.androidgit.R
import com.marcosfigueroa.androidgit.adapter.AdapterClientes
import com.marcosfigueroa.androidgit.adapter.OnClienteClickListener
import com.marcosfigueroa.androidgit.model.Cliente
import com.marcosfigueroa.androidgit.repository.Repository
import com.marcosfigueroa.androidgit.viewmodel.MainViewModel
import com.marcosfigueroa.androidgit.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_clientes.*

class ClientesActivity : AppCompatActivity(), OnClienteClickListener {

    private lateinit var sp: SharedPreferences
    private lateinit var viewModel: MainViewModel
    var adapterClientes = AdapterClientes(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clientes)

        // Hide action bar
        supportActionBar?.hide()

        // Setup recyclerView
        setupRecyclerview()

        // Data
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getClientes()
        viewModel.responseClientes.observe(this, Observer{ response ->
            if (response.isSuccessful) {
                val clientesList = response.body()?.Clientes
                adapterClientes.setData(clientesList!!)
            }
        })

        // Boton regresar
        btnRegresar.setOnClickListener {
            startActivity(Intent(this, InicioActivity::class.java))
            finish()
        }

    }

    private fun setupRecyclerview() {
        recyclerClientes.adapter = adapterClientes
        recyclerClientes.layoutManager = LinearLayoutManager(this)
    }

    override fun itemClick(item: Cliente, position: Int) {
        val cliente = item
        // sharedPreferences
        sp = getSharedPreferences("pedidos", Context.MODE_PRIVATE)
        var editor = sp.edit()
        editor.putString("idCliente", cliente.id.toString())
        editor.putString("cliente", cliente.nombre)
        editor.apply()
        // startActivity
        startActivity(Intent(this, ProductosActivity::class.java))
        finish()
    }
}