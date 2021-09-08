package com.appssquare.androidtask.UI.views.Login.data
import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String,
    @SerializedName("email") var email : String
)
