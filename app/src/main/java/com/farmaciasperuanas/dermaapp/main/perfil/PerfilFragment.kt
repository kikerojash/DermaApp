package com.farmaciasperuanas.dermaapp.main.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.farmaciasperuanas.dermaapp.databinding.FragmentPerfilBinding

class PerfilFragment :Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }
}