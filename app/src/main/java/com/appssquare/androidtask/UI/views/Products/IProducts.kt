package com.appssquare.androidtask.UI.views.Products

interface IProducts {
    suspend fun fetchProducts(token :String ,skip : Int ,search :String)
}