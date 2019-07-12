package com.farmaciasperuanas.dermaapp.model

import androidx.room.Entity
import com.squareup.moshi.Json

@Entity(tableName = "categorias", primaryKeys = ["idCategoria"])
class Categorias(
    @Json(name = "idCategoria")
    val idCategoria: String,
    @Json(name = "nombreCategoria")
    val nombreCategoria: String?,
    @Json(name = "imagenCategoria")
    val imagenCategoria: String?,
    @Json(name = "cantidaProductos")
    val cantidaProductos: String
)