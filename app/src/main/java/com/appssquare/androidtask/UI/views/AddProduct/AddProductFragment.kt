package com.appssquare.androidtask.UI.views.AddProduct

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.appssquare.androidtask.R
import com.appssquare.androidtask.UI.views.AddProduct.data.AddProductInput
import com.appssquare.androidtask.Utils.Constants.Token
import com.appssquare.androidtask.Utils.Resource
import com.appssquare.androidtask.Utils.ShowToast
import com.appssquare.androidtask.network.Repository.MainRepository
import com.appssquare.androidtask.network.VMProviderFactory.ViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_add_product.*

class AddProductFragment : Fragment() {
    lateinit var addProductViewModel: AddProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_add_product, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {
        val repository = MainRepository()
        val factory = ViewModelProviderFactory(this.requireActivity().application, repository)
        addProductViewModel = ViewModelProvider(this.viewModelStore, factory).get(AddProductViewModel::class.java)
        btnAddProduct.setOnClickListener {
            var name = etName.text.toString()
            val price = etPrice.text.toString()
            val quantity = etQuantity.text.toString()
            Log.d("onLoginClick", "$name / $price / $quantity")
            if (price.isNotEmpty() && name.isNotEmpty()) {
                addProductViewModel.AddProducts(Token,
                    AddProductInput(name,price,quantity))
                loading.visibility = View.VISIBLE
                observeProducts()
            }else{
                ShowToast("please, enter Email and Password")
            }
        }
    }

    private fun observeProducts() {
        addProductViewModel.productsData.observe(this.viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    loading.visibility = View.GONE
                    response.data?.let { productsResponse ->
                        activity?.onBackPressed()
                    }
                }

                is Resource.Error -> {
                    loading.visibility = View.GONE
                    response.message?.let { message ->
                    }
                }

                is Resource.Loading -> {
                    loading.visibility = View.VISIBLE
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