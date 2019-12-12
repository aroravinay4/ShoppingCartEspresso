package com.espresso.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.espresso.R
import com.espresso.activity.ProductDetailActivity
import com.espresso.datasource.Product
import com.espresso.utils.Utility.Companion.getImage

class CartListAdapter(val context: Context, val productList: ArrayList<Product>) :
    RecyclerView.Adapter<CartListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_cart_item, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(productList[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("product_detail", productList[position])
            context.startActivity(intent)

        }

    }


    override fun getItemCount(): Int {
        return productList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(product: Product) {
            val productName = itemView.findViewById(R.id.product_name) as TextView
            productName.text = product.productName
            val productDescription = itemView.findViewById(R.id.product_description) as TextView
            productDescription.setText(product.description)
            val productQuantity = itemView.findViewById(R.id.item_quantity) as TextView
            productQuantity.text = "Quantity: ${product.quantity}"


        }


    }


}