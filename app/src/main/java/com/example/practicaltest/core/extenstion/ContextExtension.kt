package com.example.practicaltest.core.extenstion

import android.Manifest
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
inline fun Context.withNetwork(available: () -> Unit, error: () -> Unit) {
    if (isNetworkStatusAvailable()) {
        available()
    } else {
        error()
    }
}

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isNetworkStatusAvailable(): Boolean {
    val connectivityManager = this
        .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        val netInfo = it.activeNetworkInfo
        netInfo?.let {
            if (netInfo.isConnected) return true
        }
    }
    return false
}

fun Context.getCompatDrawable(@DrawableRes drawableRes: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableRes)