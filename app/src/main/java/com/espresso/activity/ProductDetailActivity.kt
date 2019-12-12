package com.espresso.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.espresso.R
import com.espresso.datasource.Product
import com.espresso.utils.SharedPreferenceHelper
import com.espresso.utils.Utility.Companion.getImage
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : BaseActivity(), View.OnClickListener {

    private var productDetails: Product? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        //getIntent data
        if (intent.extras != null)
            productDetails = intent.getParcelableExtra<Product>("product_detail")
        initializeView()
        setProductData()

        getCartCount()
    }


    override fun onResume() {
        super.onResume()
        getCartCount()
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.add_to_cart_btn -> {
                itemAddToCart()
            }
        }
    }

    private fun setProductData() {
        if (productDetails != null) {
            product_name.setText(productDetails!!.productName)
            Glide.with(this)
                .load(getImage(productDetails!!.image, this@ProductDetailActivity))
                .into(product_image)

            product_description.setText(productDetails!!.description)
        }
    }

    private fun initializeView() {
        val toolBar: View = findViewById(R.id.toolbar)
        initializeToolBar(toolBar, "Product Detail")
        add_to_cart_btn.setOnClickListener(this)

    }


    private fun itemAddToCart() {
        productDetails!!.quantity = Integer.parseInt(product_quantity.selectedItem.toString())
        SharedPreferenceHelper.exists(productDetails!!, this)

        getCartCount()
    }


}
