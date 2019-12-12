package com.espresso.utils

import android.content.Context
import android.preference.PreferenceManager
import com.espresso.activity.BaseActivity
import com.espresso.datasource.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferenceHelper {

    companion object {
        val KEY: String = "cart_list"
        private val PREF_NAME: String = SharedPreferenceHelper::javaClass.name
        fun saveDataInSharedPref(list: ArrayList<Product>, context: Context) {
            val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            val gson = Gson()
            val json = gson.toJson(list)
            editor.putString(KEY, json)
            editor.apply()     // This line is IMPORTANT !!!
        }

        fun getDataFromSharedPref(context: Context): ArrayList<Product> {
            val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val gson = Gson()
            val json = prefs.getString(KEY, null)
            val type = object : TypeToken<ArrayList<Product>>() {}.getType()
            if (json != null) {
                return gson.fromJson(json, type)
            } else {
                return ArrayList<Product>();
            }
        }

        fun clearSharedPreference(context: Context) {
            val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            var editor = prefs.edit().clear().commit()
        }

        fun exists(newProduct: Product, context: Context) {
            var arrayList = getDataFromSharedPref(context)
            var isFound = false
            for (item in arrayList) {
                if (item.id == newProduct.id) {
                    item.quantity = item.quantity + newProduct.quantity
                    isFound = true
                    break
                }
            }
            if (!isFound) {
                arrayList.add(newProduct)
            }
            saveDataInSharedPref(arrayList, context)
        }


    }
}