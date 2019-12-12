package com.espresso.utils

import android.content.Context

class Utility {
    companion object {

        fun getImage(imageName: String,context: Context): Int {
            val drawableResourceId: Int =
                context.getResources()
                    .getIdentifier(imageName, "drawable", context.getPackageName());

            return drawableResourceId
        }
    }
}