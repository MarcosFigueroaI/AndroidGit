package com.marcosfigueroa.androidgit.repository

import com.marcosfigueroa.androidgit.api.RetrofitInstance
import com.marcosfigueroa.androidgit.model.*
import retrofit2.Response

class Repository {

    suspend fun login(user: String, pwd: String): Response<Usuarios> {
        return RetrofitInstance.api.login(user, pwd)
    }

    suspend fun getClientes(): Response<Clientes> {
        return RetrofitInstance.api.getClientes()
    }

}