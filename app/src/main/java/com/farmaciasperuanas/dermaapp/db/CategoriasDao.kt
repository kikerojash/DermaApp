package com.farmaciasperuanas.dermaapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.farmaciasperuanas.dermaapp.model.Categorias

@Dao
interface CategoriasDao {

    @Query("SELECT * FROM categorias")
    fun obtenerListaCategorias( ): LiveData<List<Categorias>>

    @Insert(onConflict = REPLACE)
    fun guardarListaCategorias(matches: List<Categorias?>)
}