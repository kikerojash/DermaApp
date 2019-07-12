package com.farmaciasperuanas.dermaapp.main.promocion

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PromocionFactory (
    private val repository: PromocionRepository,
    private val application: Application
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PromocionViewModel(application, repository) as T
    }
}