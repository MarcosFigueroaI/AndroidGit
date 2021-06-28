package com.marcosfigueroa.androidgit.repository

import com.marcosfigueroa.androidgit.api.RetrofitInstance
import com.marcosfigueroa.androidgit.model.User
import com.marcosfigueroa.androidgit.model.Usuario
import com.marcosfigueroa.androidgit.model.Usuarios
import retrofit2.Response

class Repository {

    suspend fun login(user: String, pwd: String): Response<Usuarios> {
        return RetrofitInstance.api.login(user, pwd)
    }

}