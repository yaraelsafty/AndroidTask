package com.appssquare.androidtask.UI.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appssquare.androidtask.R
import com.appssquare.androidtask.UI.views.Products.ProductFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) { // initial transaction should be wrapped like this
        var fragment = ProductFragment()
            supportFragmentManager.beginTransaction().add(R.id.container, fragment)
                .commit()
        }

    }
}