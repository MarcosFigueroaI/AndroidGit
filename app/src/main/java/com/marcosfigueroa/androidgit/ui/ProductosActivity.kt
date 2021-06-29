package com.marcosfigueroa.androidgit.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcosfigueroa.androidgit.R
import com.marcosfigueroa.androidgit.adapter.AdapterProductos
import com.marcosfigueroa.androidgit.adapter.OnProductoClickListener
import com.marcosfigueroa.androidgit.model.Cliente
import com.marcosfigueroa.androidgit.model.Producto
import com.marcosfigueroa.androidgit.repository.Repository
import com.marcosfigueroa.androidgit.viewmodel.MainViewModel
import com.marcosfigueroa.androidgit.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_productos.*

class ProductosActivity : AppCompatActivity(), OnProductoClickListener {

    private lateinit var sp: SharedPreferences
    private lateinit var viewModel: MainViewModel
    var adapterProductos = AdapterProductos(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        // Hide action bar
        supportActionBar?.hide()

        // Setup recyclerView
        setupRecyclerview()

        // Data
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getProductos()
        viewModel.responseProductos.observe(this, Observer { response ->
            if (response.isSuccessful) {
                val productosList = response.body()?.Productos
                adapterProductos.setData(productosList!!)
            }
        })

        // Boton regresar
        btnRegresar.setOnClickListener {
            startActivity(Intent(this, ClientesActivity::class.java))
            finish()
        }

    }

    private fun setupRecyclerview() {
        recyclerProductos.adapter = adapterProductos
        recyclerProductos.layoutManager = LinearLayoutManager(this)
    }

    override fun itemClick(item: Producto, position: Int) {
        val producto = item
        // sharedPreferences
        sp = getSharedPreferences("pedidos", Context.MODE_PRIVATE)
        var editor = sp.edit()
        editor.putString("idProducto", producto.id.toString())
        editor.putString("producto", producto.nombre)
        editor.apply()
        // startActivity
        //startActivity(Intent(this, ProductosActivity::class.java))
        //finish()
    }
}