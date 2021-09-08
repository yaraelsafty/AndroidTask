package com.appssquare.androidtask.UI.views.AddProduct

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.appssquare.androidtask.R
import com.appssquare.androidtask.UI.views.Products.Adapter.ProductsAdapter
import com.appssquare.androidtask.UI.views.Products.ProductsViewModel
import com.appssquare.androidtask.Utils.Constants
import com.appssquare.androidtask.Utils.Resource
import com.appssquare.androidtask.Utils.ShowToast
import com.appssquare.androidtask.network.Repository.MainRepository
import com.appssquare.androidtask.network.VMProviderFactory.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*

class AddProductFragment : Fragment() {
    lateinit var addProductViewModel: AddProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_product, container, false)
    }
    private fun init() {

    }

    private fun observeProducts() {
        addProductViewModel.productsData.observe(this, Observer { response ->
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

                    }
                }

                is Resource.Error -> {
//                    hideProgressBar()
                    response.message?.let { message ->
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
            AddProductFragment()
            }
}