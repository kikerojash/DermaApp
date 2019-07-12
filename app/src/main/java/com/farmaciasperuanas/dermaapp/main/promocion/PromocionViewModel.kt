package com.farmaciasperuanas.dermaapp.main.promocion

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.farmaciasperuanas.dermaapp.base.AbsentLiveData
import com.farmaciasperuanas.dermaapp.base.Resource
import com.farmaciasperuanas.dermaapp.model.Productos

class PromocionViewModel(
    application: Application,
    val promocionRepository: PromocionRepository
) : ViewModel() {

    val TAG = PromocionViewModel::class.java.name

    val _query = MutableLiveData<String>()

    val players: LiveData<Resource<List<Productos>>> = Transformations.switchMap(_query) { id ->
        if (id.isNullOrBlank()) {
            AbsentLiveData.create()
        } else {
            promocionRepository.obtenerProductosLista(id)
        }
    }

    fun initData(idEvent: String) {
        _query.value = idEvent
    }

}