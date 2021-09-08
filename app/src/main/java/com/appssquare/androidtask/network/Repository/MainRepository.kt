package com.appssquare.androidtask.network.Repository

import com.appssquare.androidtask.UI.views.AddProduct.data.AddProductInput
import com.appssquare.androidtask.UI.views.Login.data.LoginInput
import com.appssquare.androidtask.network.RetrofitInstance

class MainRepository {

    suspend fun loginUser(input: LoginInput) = RetrofitInstance.Api.login(input)
    suspend fun getProducts (token : String , skip : Int,search :String) = RetrofitInstance.Api.getProducts(token,skip,search)
    suspend fun AddProduct (token : String , input :AddProductInput) = RetrofitInstance.Api.AddProduct(token,input)
}