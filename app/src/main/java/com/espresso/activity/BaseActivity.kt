package com.espresso.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.espresso.R

import com.espresso.datasource.Product
import com.espresso.utils.SharedPreferenceHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.toolbar.*


abstract class BaseActivity : AppCompatActivity() {

    lateinit var view: View

    fun initializeToolBar(view: View, title: String) {
        this.view = view
        val titleTextView: TextView = view.findViewById(R.id.toolbar_title) as TextView
        titleTextView.setText(title)

        val cartImageView: ImageView = view.findViewById(R.id.imageViewCart)
        val homeIcon: ImageView = view.findViewById(R.id.home_icon)
        val view: View = view.findViewById(R.id.cart_icon_view)

        if (title.equals("Product Detail")) {
            view.visibility = VISIBLE
            homeIcon.setImageResource(R.drawable.ic_arrow_back_black_24dp);
            homeIcon.setOnClickListener {
                onBackPressed()
            }
        } else if (title.equals("Cart")) {
            homeIcon.setImageResource(R.drawable.ic_arrow_back_black_24dp);
            view.visibility = GONE
            homeIcon.setOnClickListener {
                onBackPressed()
            }
        } else {
            view.visibility = VISIBLE
            homeIcon.setImageResource(R.drawable.ic_menu_black_24dp);

        }
        cartImageView.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

    }

    fun getCartCount() {
        var arrayList = SharedPreferenceHelper.getDataFromSharedPref(this)
        if (arrayList.size > 0) {
            var textViewCount: TextView = view.findViewById(R.id.textViewCount)
            textViewCount.visibility = View.VISIBLE
            textViewCount.text = arrayList.size.toString()
        } else {
            textViewCount.visibility = View.GONE
        }
    }


}