package com.marcosfigueroa.androidgit.api

import com.google.gson.GsonBuilder
import com.marcosfigueroa.androidgit.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttp = OkHttpClient.Builder().addInterceptor(logger)

    var gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttp.build())
            .build()
    }

    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }

}