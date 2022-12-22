package com.alexeyyuditsky.holybibleapp.core

import android.util.Log

fun <T> log(message: T) {
    Log.d("MyLog", message.toString())
}