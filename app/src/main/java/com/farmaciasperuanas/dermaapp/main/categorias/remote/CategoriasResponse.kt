package com.farmaciasperuanas.dermaapp.main.categorias.remote

import com.farmaciasperuanas.dermaapp.model.Categorias
import com.squareup.moshi.Json

data class CategoriasResponse(
    @Json(name = "listaCategorias")
    val listaCategorias: List<Categorias?>?
)