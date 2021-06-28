package com.marcosfigueroa.androidgit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.marcosfigueroa.androidgit.R
import com.marcosfigueroa.androidgit.model.Cliente
import kotlinx.android.synthetic.main.lista_clientes.view.*

class AdapterClientes(var clickListener: OnItemClickListener): RecyclerView.Adapter<AdapterClientes.MyViewHolder>() {

    var clientesList = emptyList<Cliente>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nombre = itemView.tvCliente

        fun initialize(item: Cliente, action:OnItemClickListener) {
            nombre.text = item.nombre_cliente
            itemView.setOnClickListener {
                action.itemClick(item, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lista_clientes, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //val item = clientesList[position]
        holder.initialize(clientesList.get(position), clickListener)
    }

    override fun getItemCount(): Int {
        return clientesList.size
    }

    fun setData(newList: List<Cliente>) {
        clientesList = newList
        notifyDataSetChanged()
    }
}

interface OnItemClickListener {
    fun itemClick(item: Cliente, position: Int) {}
}