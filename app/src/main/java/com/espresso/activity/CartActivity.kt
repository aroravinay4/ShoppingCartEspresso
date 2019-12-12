package com.espresso.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.espresso.R
import com.espresso.adapter.CartListAdapter
import com.espresso.adapter.ProductListAdapter
import com.espresso.datasource.Product
import com.espresso.datasource.ProductData
import com.espresso.utils.SharedPreferenceHelper
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_list_product.*
import kotlinx.android.synthetic.main.activity_list_product.recycler_view

class CartActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        initView()

    }


    private fun initView() {
        var arrayList: ArrayList<Product> = SharedPreferenceHelper.getDataFromSharedPref(this)
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = CartListAdapter(this, arrayList)
        recycler_view.adapter = adapter
        check_out.setOnClickListener(this)
        val toolBar: View = findViewById(R.id.toolbar)
        initializeToolBar(toolBar, "Cart")

    }

    private fun clearSharedPreference() {
        SharedPreferenceHelper.clearSharedPreference(this)
        recycler_view.visibility = View.GONE
        check_out.visibility = View.GONE
        text_view_buy.visibility = View.VISIBLE
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.check_out -> {
                clearSharedPreference()
            }
        }
    }


}
