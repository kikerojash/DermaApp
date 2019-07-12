package com.farmaciasperuanas.dermaapp.main.promocion.json

import com.farmaciasperuanas.dermaapp.model.Productos
import com.squareup.moshi.Json


data class SchedulesResponse(
    @Json(name = "listaProductos")
    val listaProductos: List<Productos?>?
)