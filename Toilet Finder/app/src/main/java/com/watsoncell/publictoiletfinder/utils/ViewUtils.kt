package com.watsoncell.publictoiletfinder.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun View.snackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).let { snackBar ->
        snackBar.setAction("OK") {
            snackBar.dismiss()
        }
    }.show()
}

fun Context.toast(message: String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}
