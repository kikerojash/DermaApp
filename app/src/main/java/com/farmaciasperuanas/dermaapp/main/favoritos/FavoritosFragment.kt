package com.farmaciasperuanas.dermaapp.main.favoritos


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.farmaciasperuanas.dermaapp.databinding.FragmentFavoritosBinding


class FavoritosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        return binding.root
    }
}