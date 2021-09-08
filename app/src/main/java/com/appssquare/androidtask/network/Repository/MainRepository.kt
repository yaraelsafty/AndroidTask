package com.appssquare.androidtask.network.Repository

import com.appssquare.androidtask.UI.views.Login.data.LoginInput
import com.appssquare.androidtask.network.RetrofitInstance

class MainRepository {

    suspend fun loginUser(input: LoginInput) = RetrofitInstance.Api.login(input)
    suspend fun getProducts (token : String , skip : Int) = RetrofitInstance.Api.getProducts(token,skip)
}