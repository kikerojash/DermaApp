package com.farmaciasperuanas.dermaapp.main.promocion

import android.util.Log
import androidx.lifecycle.LiveData
import com.farmaciasperuanas.dermaapp.api.DermaService
import com.farmaciasperuanas.dermaapp.base.ApiResponse
import com.farmaciasperuanas.dermaapp.base.ContextProviders
import com.farmaciasperuanas.dermaapp.base.NetworkBoundResource
import com.farmaciasperuanas.dermaapp.base.Resource
import com.farmaciasperuanas.dermaapp.db.DermaDb
import com.farmaciasperuanas.dermaapp.db.PromocionDao
import com.farmaciasperuanas.dermaapp.login.dao.LoginDao
import com.farmaciasperuanas.dermaapp.main.promocion.json.SchedulesResponse
import com.farmaciasperuanas.dermaapp.model.Productos

class PromocionRepository private constructor(
    private val db: DermaDb,
    //  private val loginDao: LoginDao,
    private val promocionDao: PromocionDao,
    private val dermaService: DermaService,
    private val coroutineContext: ContextProviders
) {

    val TAG = PromocionFragment::class.java.name

    fun obtenerProductosLista(teamId: String): LiveData<Resource<List<Productos>>> {
        return object : NetworkBoundResource<List<Productos>, SchedulesResponse>(coroutineContext) {

            override fun saveCallResult(item: SchedulesResponse) {
                item.listaProductos?.let { players ->
                    promocionDao.guardarListaPromocion(players)
                }
            }
            override fun createCall(): LiveData<ApiResponse<SchedulesResponse>> = dermaService.obtenerProductoListaService()

            override fun shouldFetch(data: List<Productos>?): Boolean = data?.isEmpty() ?: true

            override fun loadFromDb(): LiveData<List<Productos>> = promocionDao.obtenerListaPromocion()

        }.asLiveData()
    }


    companion object {
        private var INSTANCE: PromocionRepository? = null

        fun getInstance(
            dermaDb: DermaDb,
            dermaService: DermaService
        ): PromocionRepository = INSTANCE
            ?: synchronized(PromocionRepository::class.java) {
                PromocionRepository(
                    dermaDb,
                    dermaDb.promocionDao(),
                    dermaService,
                    ContextProviders.getInstance()
                )
                    .also { INSTANCE = it }
            }
    }
}