package com.marcosfigueroa.androidgit.api

import com.marcosfigueroa.androidgit.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("login.php")
    suspend fun login(
        //@Body user: User
        @Field("user") user: String,
        @Field("pwd") pwd: String
    ): Response<Usuarios>

    @GET("clientes.php")
    suspend fun getClientes(): Response<Clientes>

    @GET("productos.php")
    suspend fun getProductos(): Response<Productos>

}