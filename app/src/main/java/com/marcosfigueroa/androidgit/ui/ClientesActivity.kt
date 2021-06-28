package com.marcosfigueroa.androidgit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcosfigueroa.androidgit.R
import com.marcosfigueroa.androidgit.adapter.AdapterClientes
import com.marcosfigueroa.androidgit.adapter.OnItemClickListener
import com.marcosfigueroa.androidgit.model.Cliente
import com.marcosfigueroa.androidgit.repository.Repository
import com.marcosfigueroa.androidgit.viewmodel.MainViewModel
import com.marcosfigueroa.androidgit.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_clientes.*

class ClientesActivity : AppCompatActivity(), OnItemClickListener {

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
        println(cliente.nombre_cliente)
        MainActivity().mostrarAlerta(this, "Cliente", cliente.nombre_cliente)

    }
}