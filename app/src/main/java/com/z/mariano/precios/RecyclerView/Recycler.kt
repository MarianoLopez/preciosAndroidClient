package com.z.mariano.precios.RecyclerView

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.z.mariano.precios.Model.DataClasses
import com.z.mariano.precios.R
import kotlinx.android.synthetic.main.items.view.*


/**
 * Created by Mariano on 20-ene-18.
 */

class ProductAdapter(private val productos: List<DataClasses.Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val e = productos[position]
        holder?.bindItems(e)
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)  {
        fun bindItems(product: DataClasses.Product) = with(itemView)  {
            nombre.text = product.name
            productId.text = "ID: ${product.id}"
            fecha.text = "Actualización: ${product.date}"
            monto.text = product.price.toString()
        }
    }
}