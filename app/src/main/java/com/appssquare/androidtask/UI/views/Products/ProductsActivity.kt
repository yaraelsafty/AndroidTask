package com.appssquare.androidtask.UI.views.Products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.appssquare.androidtask.R
import com.appssquare.androidtask.UI.views.Products.Adapter.ProductsAdapter
import com.appssquare.androidtask.Utils.Constants.Token
import com.appssquare.androidtask.Utils.Resource
import com.appssquare.androidtask.Utils.ShowToast
import com.appssquare.androidtask.network.Repository.MainRepository
import com.appssquare.androidtask.network.VMProviderFactory.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*

class ProductsActivity : AppCompatActivity() {
    lateinit var productsViewModel: ProductsViewModel
    private lateinit var adapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val repository = MainRepository()
        val factory = ViewModelProviderFactory(application, repository)
        productsViewModel = ViewModelProvider(this, factory).get(ProductsViewModel::class.java)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                productsViewModel.getProducts(Token,0 , s.toString())
                observeProducts()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        productsViewModel.getProducts(Token,0 , etSearch.text.toString())
        observeProducts()
    }

    private fun observeProducts() {
        productsViewModel.productsData.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
//                    hideProgressBar()
                    response.data?.let { productsResponse ->
                        val linearLayoutManager = LinearLayoutManager(this)
                        rvProducts.layoutManager = linearLayoutManager
                        rvProducts.addItemDecoration(
                            DividerItemDecoration(
                                this,
                                linearLayoutManager.orientation
                            )
                        )
                        adapter = ProductsAdapter(this, response.data.data)
                        rvProducts.adapter = adapter

                    }
                }

                is Resource.Error -> {
//                    hideProgressBar()
                    response.message?.let { message ->
                        ShowToast(message)
                    }
                }

                is Resource.Loading -> {
//                    showProgressBar()
                }
            }
        })

    }

}