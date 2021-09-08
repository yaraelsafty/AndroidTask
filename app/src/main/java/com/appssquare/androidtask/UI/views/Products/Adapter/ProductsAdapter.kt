package com.appssquare.androidtask.UI.views.Products.Adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
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
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.product_item.view.*

class ProductsAdapter (private val context: Context, private val items: List<ProductData>) :
    RecyclerView.Adapter<ProductsAdapter.productsViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productsViewHolder =
        productsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        )

    override fun onBindViewHolder(holder: productsViewHolder, position: Int) {
        val item = items[position]
        holder.tvPrice.text = item.price.toString()
        holder.tvName.text = item.name
        Glide.with(context)
            .load(item.image)
            .into(holder.ivProduct)

        val content = "Product name: ${item.name},Image :${item.image} , price: ${item.price} , Quantity: ${item.quantity} "

        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        holder.ivQR.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class productsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvPrice: TextView = itemView.tvPrice
        val tvName: TextView = itemView.tvName
        val ivProduct: ImageView = itemView.ivProduct
        val ivQR: ImageView = itemView.ivQR

    }



    companion object {
        private const val TAG = "HomeAdapter"
    }

}