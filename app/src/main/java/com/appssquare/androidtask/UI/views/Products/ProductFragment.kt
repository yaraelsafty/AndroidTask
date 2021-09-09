package com.appssquare.androidtask.UI.views.Products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.appssquare.androidtask.R
import com.appssquare.androidtask.UI.views.AddProduct.AddProductFragment
import com.appssquare.androidtask.UI.views.Products.Adapter.ProductsAdapter
import com.appssquare.androidtask.Utils.Constants
import com.appssquare.androidtask.Utils.Resource
import com.appssquare.androidtask.Utils.ShowToast
import com.appssquare.androidtask.Utils.replaceFragment
import com.appssquare.androidtask.network.Repository.MainRepository
import com.appssquare.androidtask.network.VMProviderFactory.ViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_product.*


class ProductFragment : Fragment() {
    lateinit var productsViewModel: ProductsViewModel
    private lateinit var adapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {
        val repository = MainRepository()
        val factory = ViewModelProviderFactory(this.requireActivity().application, repository)
        productsViewModel = ViewModelProvider(this, factory).get(ProductsViewModel::class.java)
        productsViewModel.getProducts(Constants.Token,0 , "")
        shimmerLayout.startShimmer()

        btnSearch.setOnClickListener {
            productsViewModel.getProducts(Constants.Token,0 , etSearch.toString())
            observeProducts()
        }
        btnAdd.setOnClickListener {
            replaceFragment(AddProductFragment.newInstance(),R.id.container,false)
//
        }
        observeProducts()
    }

    private fun observeProducts() {
        productsViewModel.productsData.observe(this.viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
//                    hideProgressBar()
                    response.data?.let { productsResponse ->
                        val linearLayoutManager = LinearLayoutManager(this.context)
                        rvProducts.layoutManager = linearLayoutManager
                        rvProducts.addItemDecoration(
                            DividerItemDecoration(
                                this.context,
                                linearLayoutManager.orientation
                            )
                        )
                        if(response.data.data.isNotEmpty()){
                            shimmerLayout.stopShimmer()
                            shimmerLayout.visibility= View.GONE
                            adapter = ProductsAdapter(this.requireContext(), response.data.data)
                            rvProducts.adapter = adapter
                        }else{
                            btnSearch.visibility = View.GONE
                            etSearch.visibility = View.GONE
                            ShowToast("no products founded")
                        }
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
    companion object {
        @JvmStatic
        fun newInstance() =
            ProductFragment()
            }
    }
