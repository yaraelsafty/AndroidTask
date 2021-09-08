package com.appssquare.androidtask.UI.views.Products

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.appssquare.androidtask.UI.views.Products.data.ProductResponse
import com.appssquare.androidtask.Utils.Resource
import com.appssquare.androidtask.network.Repository.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductsViewModel (app: Application,
                         private val mainRepository: MainRepository
) : AndroidViewModel(app) , IProducts {
    val productsData: MutableLiveData<Resource<ProductResponse>> = MutableLiveData()


    fun getProducts(token :String , skip : Int) = viewModelScope.launch {
        fetchProducts(token,skip)
    }
    private suspend fun fetchProducts(token :String ,skip : Int) {
        productsData.postValue(Resource.Loading())
        val response = mainRepository.getProducts(token,skip)
        productsData.postValue(handleProductsResponse(response))
//        try {
//            if (hasInternetConnection(getApplication<MyApplication>())) {
//                val response = mainRepository.getProducts(skip)
//                productsData.postValue(handleProductsResponse(response))
//            } else {
//                productsData.postValue(Resource.Error(getApplication<MyApplication>().getString(R.string.no_internet_connection)))
//            }
//        } catch (t: Throwable) {
//            when (t) {
//                is IOException -> productsData.postValue(
//                    Resource.Error(
//                        getApplication<MyApplication>().getString(
//                            R.string.network_failure
//                        )
//                    )
//                )
//                else -> productsData.postValue(
//                    Resource.Error(
//                        getApplication<MyApplication>().getString(
//                            R.string.conversion_error
//                        )
//                    )
//                )
//            }
 //       }
    }

    private fun handleProductsResponse(response: Response<ProductResponse>): Resource<ProductResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}