package com.appssquare.androidtask.UI.views.AddProduct.data

import com.google.gson.annotations.SerializedName

data class AddProductResponse(
    @SerializedName("status") var status : Boolean,
    @SerializedName("message") var message : String,
    @SerializedName("data") var data : String)
