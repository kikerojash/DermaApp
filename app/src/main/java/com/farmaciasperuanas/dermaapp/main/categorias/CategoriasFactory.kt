package com.farmaciasperuanas.dermaapp.main.categorias

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CategoriasFactory (
    private val repository: CategoriasRepository,
    private val application: Application
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoriasViewModel(application, repository) as T
    }
}