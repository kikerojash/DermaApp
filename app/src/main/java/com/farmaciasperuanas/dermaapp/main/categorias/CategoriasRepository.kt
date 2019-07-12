package com.farmaciasperuanas.dermaapp.main.categorias

import androidx.lifecycle.LiveData
import com.farmaciasperuanas.dermaapp.api.DermaService
import com.farmaciasperuanas.dermaapp.base.ApiResponse
import com.farmaciasperuanas.dermaapp.base.ContextProviders
import com.farmaciasperuanas.dermaapp.base.NetworkBoundResource
import com.farmaciasperuanas.dermaapp.base.Resource
import com.farmaciasperuanas.dermaapp.db.CategoriasDao
import com.farmaciasperuanas.dermaapp.db.DermaDb
import com.farmaciasperuanas.dermaapp.main.categorias.remote.CategoriasResponse
import com.farmaciasperuanas.dermaapp.model.Categorias

class CategoriasRepository private constructor(
    private val db: DermaDb,
    private val categoriasDao: CategoriasDao,
    private val dermaService: DermaService,
    private val coroutineContext: ContextProviders
) {

    fun obtenerProductosCategoria(teamId: String): LiveData<Resource<List<Categorias>>> {
        return object : NetworkBoundResource<List<Categorias>, CategoriasResponse>(coroutineContext) {

            override fun saveCallResult(item: CategoriasResponse) {
                item.listaCategorias?.let { categorias ->
                    categoriasDao.guardarListaCategorias(categorias)
                }
            }

            override fun createCall(): LiveData<ApiResponse<CategoriasResponse>> =
                dermaService.obtenerCategoriasListaService()

            override fun shouldFetch(data: List<Categorias>?): Boolean = data?.isEmpty() ?: true

            override fun loadFromDb(): LiveData<List<Categorias>> = categoriasDao.obtenerListaCategorias()

        }.asLiveData()
    }

    companion object {
        private var INSTANCE: CategoriasRepository? = null

        fun getInstance(
            dermaDb: DermaDb,
            dermaService: DermaService
        ): CategoriasRepository = INSTANCE
            ?: synchronized(CategoriasRepository::class.java) {
                CategoriasRepository(
                    dermaDb,
                    dermaDb.categoriasDao(),
                    dermaService,
                    ContextProviders.getInstance()
                )
                    .also { INSTANCE = it }
            }
    }
}