package com.appssquare.androidtask.UI.views.Login.data

import com.google.gson.annotations.SerializedName

data class LoginInput(
    @SerializedName("email") var email : String,
    @SerializedName("password") var password : String,
    )
