package com.appssquare.androidtask.UI.views.Login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.appssquare.androidtask.UI.views.Products.ProductsActivity
import com.appssquare.androidtask.R
import com.appssquare.androidtask.Utils.Resource
import com.appssquare.androidtask.Utils.ShowToast
import com.appssquare.androidtask.UI.views.Login.data.LoginInput
import com.appssquare.androidtask.Utils.Constants.Token
import com.appssquare.androidtask.network.Repository.MainRepository
import com.appssquare.androidtask.network.VMProviderFactory.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        val repository = MainRepository()
        val factory = ViewModelProviderFactory(application, repository)
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        checkToken()
        btnLogin.setOnClickListener {
            var email = etUsername.text.toString()
            val password = etPassword.text.toString()
            Log.d("onLoginClick", "$email / $password")
            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginViewModel.loginUser(
                    LoginInput(
                        etUsername.text.toString(),
                        etPassword.text.toString()
                    )
                )
                loading.visibility = View.VISIBLE
                observeLogin()
            }else{
                ShowToast("please, enter Email and Password")
            }
        }
    }

    private fun observeLogin() {

        loginViewModel.loginResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        loading.visibility = View.GONE
                        response.data?.data?.let { Log.d("print", it.name) }
                        response.data?.let { saveToken(it.token) }
                        ShowToast("Login successfully")
                        response.data?.let { loginResponse ->
                            Intent(this@LoginActivity, ProductsActivity::class.java).also {
                                startActivity(it)
                            }
                        }
                    }

                    is Resource.Error -> {
                        loading.visibility = View.GONE
                        response.message?.let { message ->
                            ShowToast(message)
                        }
                    }

                    is Resource.Loading -> {
                        loading.visibility = View.VISIBLE

                    }
                }
            }
        })
    }
    private fun checkToken() {
        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var token : String? = sharedPreference.getString("TOKEN","")
        if (!token?.isEmpty()!!) {
            Token = token
            val homeIntent = Intent(this, ProductsActivity::class.java)
            startActivity(homeIntent)
            finish()
        }
    }
    private fun saveToken(token: String) {
        Token = token
        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putString("TOKEN",token)
        editor.commit()
    }
}