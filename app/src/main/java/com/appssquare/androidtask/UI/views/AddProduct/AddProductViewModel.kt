package com.appssquare.androidtask.UI.views.AddProduct

import android.app.Application
import android.net.wifi.WifiManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.appssquare.androidtask.UI.views.AddProduct.data.AddProductInput
import com.appssquare.androidtask.UI.views.AddProduct.data.AddProductResponse
import com.appssquare.androidtask.UI.views.Products.data.ProductResponse
import com.appssquare.androidtask.Utils.Resource
import com.appssquare.androidtask.network.Repository.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class AddProductViewModel(app: Application,
                          private val mainRepository: MainRepository
) : AndroidViewModel(app) {
    val productsData: MutableLiveData<Resource<AddProductResponse>> = MutableLiveData()

    fun AddProducts(token :String ,  input :AddProductInput) = viewModelScope.launch {
        AddProduct(token,input)
    }
     suspend fun AddProduct(token :String, input :AddProductInput) {
        productsData.postValue(Resource.Loading())
        val response = mainRepository.AddProduct(token, input)
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

    private fun handleProductsResponse(response: Response<AddProductResponse>): Resource<AddProductResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}