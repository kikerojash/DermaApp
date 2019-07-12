package com.farmaciasperuanas.dermaapp.main.promocion

import android.app.Application
import android.os.Bundle
import android.util.Log
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
import com.farmaciasperuanas.dermaapp.databinding.FragmentPromocionBinding
import com.farmaciasperuanas.dermaapp.main.promocion.adapter.PromocionAdapter

class PromocionFragment : Fragment() {


    val TAG = PromocionFragment::class.java.name

    private val promocionViewModel: PromocionViewModel by viewModels {
        Utils.providePromocionViewModelFactory(requireContext().applicationContext as Application)
    }

    private lateinit var promocionDataBinding: FragmentPromocionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        promocionDataBinding = FragmentPromocionBinding.inflate(inflater, container, false)
        initListaData()
        initData()
        return promocionDataBinding.root
    }


    private fun initListaData() {
        promocionDataBinding.recicladorPromocion.layoutManager = LinearLayoutManager(context)
        promocionDataBinding.srlList.isEnabled = false
        promocionDataBinding.recicladorPromocion.adapter = PromocionAdapter(requireContext(), Resource.loading(null),
            {
                /*OnClick Button Agregar*/
                Toast.makeText(this.context, it.descripcionProducto, Toast.LENGTH_SHORT).show()
            },
            {
                /*OnClick Button ItemGlobal*/
                Toast.makeText(this.context, it.idCategoria, Toast.LENGTH_SHORT).show()
            })


    }

    private fun initData() {
        Log.d(TAG, "initData")
        promocionViewModel.initData("asdasd")
        promocionViewModel.players.observe(this, Observer { res ->
            (promocionDataBinding.recicladorPromocion.adapter as PromocionAdapter).submitData(
                res,
                promocionDataBinding.recicladorPromocion,
                GridLayoutManager(context, 2)
            )
        })
    }


    companion object {

        private const val KEY_MATCH = "key_match"

        fun newInstance(type: String): PromocionFragment {
            val fragment = PromocionFragment()
            fragment.arguments = Bundle().apply {
                putString(KEY_MATCH, type)
            }
            return fragment
        }
    }


}