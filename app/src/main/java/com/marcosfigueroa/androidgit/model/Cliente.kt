package com.marcosfigueroa.androidgit.model

import com.google.gson.annotations.SerializedName

data class Cliente(
    @SerializedName("id_cliente")
    val id: Int,
    @SerializedName("nombre_cliente")
    val nombre: String
)