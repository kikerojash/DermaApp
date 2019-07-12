package com.farmaciasperuanas.dermaapp.main.categorias.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farmaciasperuanas.dermaapp.R
import com.farmaciasperuanas.dermaapp.base.Resource
import com.farmaciasperuanas.dermaapp.base.adapter.BaseRVAdapter
import com.farmaciasperuanas.dermaapp.model.Categorias
import kotlinx.android.synthetic.main.item_categorias.view.*

class CategoriasAdapter(
    context: Context,
    resource: Resource<List<Categorias>>,
    private val clickListener: (Categorias) -> Unit
) : BaseRVAdapter<Categorias>(context, resource) {

    override fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CategoriasItem(LayoutInflater.from(parent.context).inflate(R.layout.item_categorias, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CategoriasItem) {
            holder.bind(resource.data?.get(position), clickListener)
        }
    }

    inner class CategoriasItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(categorias: Categorias?, clickListener: (Categorias) -> Unit) {
            itemView.textViewNombreCategoria.text = categorias?.nombreCategoria
            itemView.textViewCantidadProductos.text= categorias?.cantidaProductos
            Glide.with(itemView.context)
                .load(categorias?.imagenCategoria)
                .into(itemView.imageViewCategoria)
            categorias?.let { team ->

                with(itemView) {
                    setOnClickListener {
                        clickListener(categorias)
                    }
                }
            }
        }
    }
}