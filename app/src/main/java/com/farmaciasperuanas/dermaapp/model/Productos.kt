package com.farmaciasperuanas.dermaapp.model


import androidx.room.Entity
import com.squareup.moshi.Json

@Entity(tableName = "productos", primaryKeys = ["idProducto"])
class Productos(
    @Json(name = "idProducto")
    val idProducto: String,
    @Json(name = "descripcionProducto")
    val descripcionProducto: String?,
    @Json(name = "idCategoria")
    val idCategoria: String?,
    @Json(name = "imagenProductos")
    val imagenProductos: String
)