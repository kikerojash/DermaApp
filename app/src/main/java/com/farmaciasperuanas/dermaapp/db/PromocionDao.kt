package com.farmaciasperuanas.dermaapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.farmaciasperuanas.dermaapp.model.Productos

@Dao
interface PromocionDao {

    @Query("SELECT * FROM productos")
    fun obtenerListaPromocion( ): LiveData<List<Productos>>

    @Insert(onConflict = REPLACE)
    fun guardarListaPromocion(matches: List<Productos?>)


}