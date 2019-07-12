package com.farmaciasperuanas.dermaapp.main.promocion.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farmaciasperuanas.dermaapp.R
import com.farmaciasperuanas.dermaapp.base.Resource
import com.farmaciasperuanas.dermaapp.base.adapter.BaseRVAdapter
import com.farmaciasperuanas.dermaapp.model.Productos
import kotlinx.android.synthetic.main.item_promocion.view.*

class PromocionAdapter(
    ctx: Context,
    resource: Resource<List<Productos>>,
    private val clickListener: (Productos) -> Unit,
    private val clickListenerGlobal: (Productos) -> Unit
) : BaseRVAdapter<Productos>(ctx, resource) {

    val TAG = PromocionAdapter::class.java.name

    override fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PromocionItem(LayoutInflater.from(parent.context).inflate(R.layout.item_promocion, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PromocionItem) {
            holder.bind(resource.data?.get(position), clickListener, clickListenerGlobal)
        }
    }

    inner class PromocionItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            productos: Productos?, clickListener: (Productos) -> Unit,
            clickListenerGlobal: (Productos) -> Unit
        ) {
            itemView.textViewNombreProducto.text = productos?.descripcionProducto
            Glide.with(itemView.context)
                .load(productos?.imagenProductos)
                .into(itemView.imageProductoPromocion)
            Log.d(TAG, "PromocionItem productos")
            productos?.let { team ->
                with(itemView.constraintAgregar) {
                    setOnClickListener {
                        clickListener(productos)
                    }
                }
                with(itemView) {
                    setOnClickListener {
                        clickListenerGlobal(productos)
                    }
                }

            }


        }

    }
}

