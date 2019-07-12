package com.farmaciasperuanas.dermaapp.model

import androidx.room.Entity
import com.squareup.moshi.Json

@Entity(tableName = "usuario", primaryKeys = ["idusuario"])
class Usuario(
    @Json(name = "idusuario")
    val idusuario: String,
    @Json(name = "nombreUsuario")
    val nombreUsuario: String

)