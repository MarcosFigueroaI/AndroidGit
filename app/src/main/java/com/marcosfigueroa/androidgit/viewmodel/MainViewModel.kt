package com.marcosfigueroa.androidgit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcosfigueroa.androidgit.model.*
import com.marcosfigueroa.androidgit.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<Usuarios>> = MutableLiveData()
    val responseClientes: MutableLiveData<Response<Clientes>> = MutableLiveData()
    val responseProductos: MutableLiveData<Response<Productos>> = MutableLiveData()

    fun login(user: String, pwd: String) {
        viewModelScope.launch {
            val response = repository.login(user, pwd)
            myResponse.value = response
        }
    }

    fun getClientes() {
        viewModelScope.launch {
            val response = repository.getClientes()
            responseClientes.value = response
        }
    }

    fun getProductos() {
        viewModelScope.launch {
            val response = repository.getProductos()
            responseProductos.value = response
        }
    }

}