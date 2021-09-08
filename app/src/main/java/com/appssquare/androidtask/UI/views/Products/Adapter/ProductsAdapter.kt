package com.appssquare.androidtask.UI.views.Products.Adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appssquare.androidtask.R
import com.appssquare.androidtask.UI.views.Products.data.ProductData
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.product_item.view.*

class ProductsAdapter (private val context: Context, private val items: List<ProductData>) :
    RecyclerView.Adapter<ProductsAdapter.productsViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productsViewHolder =
        productsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        )

    override fun onBindViewHolder(holder: productsViewHolder, position: Int) {
        val item = items[position]
        holder.tvPrice.text = context.getString(item.price)
        holder.tvName.text = item.name
        Glide.with(context)
            .load(item.image)
            .into(holder.ivProduct)

        holder.itemView.setOnClickListener {
//            selectedItemSingleEvent.value = context.getString(item.name)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class productsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvPrice: TextView = itemView.tvPrice
        val tvName: TextView = itemView.tvName
        val ivProduct: ImageView = itemView.ivProduct

    }



    companion object {
        private const val TAG = "HomeAdapter"
    }

}