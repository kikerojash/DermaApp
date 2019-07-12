package com.farmaciasperuanas.dermaapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.farmaciasperuanas.dermaapp.login.dao.LoginDao
import com.farmaciasperuanas.dermaapp.model.Categorias
import com.farmaciasperuanas.dermaapp.model.Productos
import com.farmaciasperuanas.dermaapp.model.Usuario


@Database(entities = [Productos::class, Usuario::class, Categorias::class], version = 1)
abstract class DermaDb : RoomDatabase() {

    abstract fun promocionDao(): PromocionDao

    abstract fun loginDao(): LoginDao

    abstract fun categoriasDao(): CategoriasDao

    companion object {

        @Volatile
        private var INSTANCE: DermaDb? = null

        fun getDatabase(context: Context): DermaDb {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, DermaDb::class.java, "derma_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }

}