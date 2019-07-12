package com.farmaciasperuanas.dermaapp.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.farmaciasperuanas.dermaapp.R
import com.farmaciasperuanas.dermaapp.databinding.FragmentDermoBinding

class DermoFragment : Fragment() {

    lateinit var dermoBinding: FragmentDermoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dermoBinding = FragmentDermoBinding.inflate(inflater, container, false)
        initView()
        return dermoBinding.root
    }

    private fun initView() {
        /*Tabs Promociones*/
        dermoBinding.tabhost.setup()
        var spec = dermoBinding.tabhost.newTabSpec(resources.getString(R.string.str_tabs_promociones))
        spec.setContent(R.id.tab1)
        spec.setIndicator(resources.getString(R.string.str_tabs_promociones))
        dermoBinding.tabhost.addTab(spec)
        /*Tabs Categorias*/
        var spec2 = dermoBinding.tabhost.newTabSpec(resources.getString(R.string.str_tabs_categorias))
        spec2.setContent(R.id.tab2)
        spec2.setIndicator(resources.getString(R.string.str_tabs_categorias))
        dermoBinding.tabhost.addTab(spec2)
        //dermoBinding.tabhost.setCurrentTab(0)


    }


}