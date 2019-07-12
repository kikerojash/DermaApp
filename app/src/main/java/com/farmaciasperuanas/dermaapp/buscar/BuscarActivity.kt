package com.farmaciasperuanas.dermaapp.buscar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.farmaciasperuanas.dermaapp.R
import com.farmaciasperuanas.dermaapp.databinding.ActivityBuscarBinding


class BuscarActivity : AppCompatActivity() {

    lateinit var buscarBinding: ActivityBuscarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buscarBinding = DataBindingUtil.setContentView(this, R.layout.activity_buscar)
    }




}