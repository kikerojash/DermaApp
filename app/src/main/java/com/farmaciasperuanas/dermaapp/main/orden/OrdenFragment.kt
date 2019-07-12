package com.farmaciasperuanas.dermaapp.main.orden

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.farmaciasperuanas.dermaapp.databinding.FragmentOrdenBinding

class OrdenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOrdenBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {
        val PAGE_NUM = "PAGE_NUM"
        fun newInstance(page: Int): OrdenFragment {
            val fragment = OrdenFragment()
            val args = Bundle()
            args.putInt(PAGE_NUM, page)
            fragment.setArguments(args)
            return fragment
        }
    }

}