package com.example.practicaltest.core.extenstion

import android.content.Context
import android.widget.Toast

fun String.showToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}