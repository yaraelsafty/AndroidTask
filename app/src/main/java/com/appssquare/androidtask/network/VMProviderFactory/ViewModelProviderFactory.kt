package com.appssquare.androidtask.network.VMProviderFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appssquare.androidtask.UI.views.AddProduct.AddProductViewModel
import com.appssquare.androidtask.network.Repository.MainRepository
import com.appssquare.androidtask.UI.views.Login.LoginViewModel
import com.appssquare.androidtask.UI.views.Products.ProductsViewModel

class ViewModelProviderFactory (
    val app: Application,
    val appRepository: MainRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(app, appRepository) as T
            }
            if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
                return ProductsViewModel(app, appRepository) as T
            }
            if (modelClass.isAssignableFrom(AddProductViewModel::class.java)) {
                return AddProductViewModel(app, appRepository) as T
            }
            throw IllegalArgumentException("Unknown class name")
        }
}