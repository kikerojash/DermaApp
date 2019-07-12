package com.farmaciasperuanas.dermaapp.carrito

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.farmaciasperuanas.dermaapp.R
import com.farmaciasperuanas.dermaapp.databinding.ActivityCarritoBinding


class CarritoActivity : AppCompatActivity() {

    lateinit var carritoBinding: ActivityCarritoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carritoBinding = DataBindingUtil.setContentView(this, R.layout.activity_carrito)
    }
}