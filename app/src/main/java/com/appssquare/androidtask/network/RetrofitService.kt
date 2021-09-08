package com.appssquare.androidtask.network

import com.appssquare.androidtask.UI.views.Login.data.LoginInput
import com.appssquare.androidtask.UI.views.Login.data.LoginResponse
import com.appssquare.androidtask.UI.views.Products.data.ProductResponse
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {
    @POST("api/login")
    suspend fun login(@Body input : LoginInput ): Response<LoginResponse>

    @GET("api/products")
    suspend fun getProducts (@Header("Authorization") token : String,  @Query("skip") skip: Int  )
    : Response<ProductResponse>

}