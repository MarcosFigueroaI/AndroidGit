package com.marcosfigueroa.androidgit.api

import com.marcosfigueroa.androidgit.model.User
import com.marcosfigueroa.androidgit.model.Usuarios
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

}