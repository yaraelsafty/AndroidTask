package com.appssquare.androidtask.UI.views.Products.data

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("status") var status : Boolean,
    @SerializedName("message") var message : String,
    @SerializedName("data") var data : List<ProductData>,
    @SerializedName("count") var count : Int
)
