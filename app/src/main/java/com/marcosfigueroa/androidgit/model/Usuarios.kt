package com.marcosfigueroa.androidgit.model

data class Usuarios(
    val data: ArrayList<Usuario>,
    val success: Boolean,
    val msg: String,
    val token: String
)