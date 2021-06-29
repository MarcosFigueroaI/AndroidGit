package com.marcosfigueroa.androidgit.model

import com.google.gson.annotations.SerializedName

data class Producto(
    @SerializedName("id_producto")
    val id: Int,
    @SerializedName("nombre_producto")
    val nombre: String,
    @SerializedName("cve_uni_com")
    val cveUnidadCompra: Int,
    @SerializedName("des_uni_com")
    val unidadCompra: String,
    @SerializedName("cve_uni_vta")
    val cveUnidadVenta: Int,
    @SerializedName("des_uni_vta")
    val unidadVenta: String,
    @SerializedName("pre_uni_com")
    val precioUnidadCompra: Double,
    @SerializedName("pre_uni_vta")
    val precioUnidadVenta: Double
)