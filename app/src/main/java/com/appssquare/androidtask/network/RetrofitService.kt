package com.appssquare.androidtask.network

import com.appssquare.androidtask.UI.views.AddProduct.data.AddProductInput
import com.appssquare.androidtask.UI.views.AddProduct.data.AddProductResponse
import com.appssquare.androidtask.UI.views.Login.data.LoginInput
import com.appssquare.androidtask.UI.views.Login.data.LoginResponse
import com.appssquare.androidtask.UI.views.Products.data.ProductResponse
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {
    @POST("api/login")
    suspend fun login(@Body input : LoginInput ): Response<LoginResponse>

    @GET("api/products")
    suspend fun getProducts (@Header("Authorization") token : String,
                             @Query("skip") skip: Int ,@Query ("search") search :String)
    : Response<ProductResponse>

    @POST("api/products")
    suspend fun AddProduct(@Header("Authorization") token : String,
                           @Body input : AddProductInput ): Response<AddProductResponse>

}