package com.farmaciasperuanas.dermaapp.main.categorias

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.farmaciasperuanas.dermaapp.base.AbsentLiveData
import com.farmaciasperuanas.dermaapp.base.Resource
import com.farmaciasperuanas.dermaapp.model.Categorias

class CategoriasViewModel(
    application: Application,
     val categoriasRepository: CategoriasRepository
) : ViewModel() {

    val _query = MutableLiveData<String>()

    val categorias: LiveData<Resource<List<Categorias>>> = Transformations.switchMap(_query) { id ->
        if (id.isNullOrBlank()) {
            AbsentLiveData.create()
        } else {
            categoriasRepository.obtenerProductosCategoria(id)
        }
    }

    fun initData(idEvent: String) {
        _query.value = idEvent
    }

}