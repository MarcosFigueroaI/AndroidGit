package com.marcosfigueroa.androidgit.api

import com.marcosfigueroa.androidgit.model.User
import com.marcosfigueroa.androidgit.model.Usuarios
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @GET("login.php")
    suspend fun logi(): Response<Usuarios>

    @FormUrlEncoded
    @POST("login.php")
    suspend fun login(
        //@Body user: User
        @Field("user") user: String,
        @Field("pwd") pwd: String
    ): Response<Usuarios>

    @Multipart
    @POST("login")
    suspend fun log(
        @Part("user") user: RequestBody?,
        @Part("pwd") pwd: RequestBody?
    ): Response<Usuarios>

}