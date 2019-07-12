package com.farmaciasperuanas.dermaapp.main.categorias

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.farmaciasperuanas.dermaapp.Utils
import com.farmaciasperuanas.dermaapp.base.Resource
import com.farmaciasperuanas.dermaapp.databinding.FragmentCategoriasBinding
import com.farmaciasperuanas.dermaapp.main.categorias.adapter.CategoriasAdapter


class CategoriasFragment : Fragment() {

    val TAG = CategoriasFragment::class.java.name

    private val categoriasViewModel: CategoriasViewModel by viewModels {
        Utils.provideCategoriasViewModelFactory(requireContext().applicationContext as Application)
    }

    private lateinit var categoriasDataBinding: FragmentCategoriasBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        categoriasDataBinding = FragmentCategoriasBinding.inflate(inflater, container, false)
        initListaData()
        initData()
        return categoriasDataBinding.root
    }

    private fun initData() {
        categoriasViewModel.initData("asdasd")
        categoriasViewModel.categorias.observe(this, Observer { res ->
            (categoriasDataBinding.recicladorCategorias.adapter as CategoriasAdapter)
                .submitData(
                    res,
                    categoriasDataBinding.recicladorCategorias,
                    GridLayoutManager(context, 2)
                )
        })


    }

    private fun initListaData() {
        categoriasDataBinding.recicladorCategorias.layoutManager = LinearLayoutManager(context)
        categoriasDataBinding.srlList.isEnabled = false
        categoriasDataBinding.recicladorCategorias.adapter =
            CategoriasAdapter(requireContext(), Resource.loading(null)) {
                /*OnClick Button Agregar*/
                Toast.makeText(this.context, it.nombreCategoria, Toast.LENGTH_SHORT).show()
            }
    }


}