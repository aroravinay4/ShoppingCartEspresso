package com.espresso.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.espresso.R

import com.espresso.adapter.ProductListAdapter
import com.espresso.datasource.ProductData
import kotlinx.android.synthetic.main.activity_list_product.*


class ProductListActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.espresso.R.layout.activity_list_product)
        initializeView()
        initRecyclerView()


    }

    private fun initializeView() {
        val toolBar: View = findViewById(R.id.toolbar)
        initializeToolBar(toolBar, "Product List")

    }

    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = ProductListAdapter(this, ProductData.productsList)
        recycler_view.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        getCartCount()
    }
}
