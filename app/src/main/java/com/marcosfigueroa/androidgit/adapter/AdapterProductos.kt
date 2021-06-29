package com.marcosfigueroa.androidgit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcosfigueroa.androidgit.R
import com.marcosfigueroa.androidgit.model.Producto
import kotlinx.android.synthetic.main.lista_productos.view.*

class AdapterProductos(var clickListener: OnProductoClickListener): RecyclerView.Adapter<AdapterProductos.MyViewHolder>() {

    var productosList = emptyList<Producto>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val producto = itemView.tvProducto

        fun initialize(item: Producto, action:OnProductoClickListener) {
            producto.text = item.nombre
            itemView.setOnClickListener {
                action.itemClick(item, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterProductos.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lista_productos, parent, false))
    }

    override fun onBindViewHolder(holder: AdapterProductos.MyViewHolder, position: Int) {
        holder.initialize(productosList.get(position), clickListener)
    }

    override fun getItemCount(): Int {
        return productosList.size
    }

    fun setData(newList: List<Producto>) {
        productosList = newList
        notifyDataSetChanged()
    }
}

interface OnProductoClickListener {
    fun itemClick(item: Producto, position: Int) {}
}