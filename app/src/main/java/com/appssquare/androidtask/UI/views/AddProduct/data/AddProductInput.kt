package com.appssquare.androidtask.UI.views.AddProduct.data

import com.google.gson.annotations.SerializedName

data class AddProductInput(
    @SerializedName("name") var name : String,
    @SerializedName("price") var price : Int,
    @SerializedName("quantity") var quantity : Int)
