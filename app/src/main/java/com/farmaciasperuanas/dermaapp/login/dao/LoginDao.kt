package com.farmaciasperuanas.dermaapp.login.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.farmaciasperuanas.dermaapp.model.Productos
import com.farmaciasperuanas.dermaapp.model.Usuario

@Dao
interface LoginDao {

    @Insert(onConflict = REPLACE)
    fun guardarUsuario(usuario: Usuario)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMatches(matches: List<Productos?>)

    /*@Query("SELECT * FROM matches WHERE idEvent = :matchId")
    fun getMatchDetail(matchId: String): LiveData<Productos>*/


}