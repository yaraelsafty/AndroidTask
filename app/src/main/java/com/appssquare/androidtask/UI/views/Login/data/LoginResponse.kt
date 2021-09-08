package com.appssquare.androidtask.UI.views.Login.data
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status") var status : Boolean,
    @SerializedName("message") var message : String,
    @SerializedName("data") var data : LoginData,
    @SerializedName("token") var token : String

)
