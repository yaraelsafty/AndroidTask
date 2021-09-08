package com.appssquare.androidtask.UI.views.Products.data

import com.google.gson.annotations.SerializedName

data class ProductData(

    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String,
    @SerializedName("price") var price : Int,
    @SerializedName("quantity") var quantity : Int,
    @SerializedName("image") var image : String
)
