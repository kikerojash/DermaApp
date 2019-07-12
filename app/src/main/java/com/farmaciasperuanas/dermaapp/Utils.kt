package com.farmaciasperuanas.dermaapp

import android.app.Application
import android.content.Context
import com.farmaciasperuanas.dermaapp.api.ServiceFactory
import com.farmaciasperuanas.dermaapp.db.DermaDb
import com.farmaciasperuanas.dermaapp.login.LoginFactory
import com.farmaciasperuanas.dermaapp.login.LoginRepository
import com.farmaciasperuanas.dermaapp.main.categorias.CategoriasFactory
import com.farmaciasperuanas.dermaapp.main.categorias.CategoriasRepository
import com.farmaciasperuanas.dermaapp.main.promocion.PromocionFactory
import com.farmaciasperuanas.dermaapp.main.promocion.PromocionRepository


object Utils {

    /*provide Logueo*/
    fun provideLoginViewModelFactory(application: Application): LoginFactory {
        return LoginFactory(
            LoginRepository.getInstance(
                DermaDb.getDatabase(application),
                ServiceFactory.getService()
            )
            , application
        )
    }
    /*Provide Producto*/
    fun providePromocionViewModelFactory(applicationContext: Application): PromocionFactory {
        return PromocionFactory(
            PromocionRepository.getInstance(
                DermaDb.getDatabase(applicationContext),
                ServiceFactory.getService()
            )
            , applicationContext
        )
    }
    /*Provide Categorias*/
    fun provideCategoriasViewModelFactory(application: Application): CategoriasFactory {
        return CategoriasFactory(
            CategoriasRepository.getInstance(
                DermaDb.getDatabase(application),
                ServiceFactory.getService()
            )
            , application
        )
    }



}