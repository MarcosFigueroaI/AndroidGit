package com.marcosfigueroa.androidgit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcosfigueroa.androidgit.model.User
import com.marcosfigueroa.androidgit.model.Usuario
import com.marcosfigueroa.androidgit.model.Usuarios
import com.marcosfigueroa.androidgit.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<Usuarios>> = MutableLiveData()

    fun login(user: String, pwd: String) {
        viewModelScope.launch {
            val response = repository.login(user, pwd)
            myResponse.value = response
        }
    }

}