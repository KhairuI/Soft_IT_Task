@file:Suppress("DEPRECATION")

package com.example.baseproject.utils.extension

import android.content.Intent
import android.os.Build
import android.os.Bundle
import java.io.Serializable

inline fun <reified T : Serializable> Bundle.serializable(key: String, m_class: Class<T>): T? =
    when {
        Build.VERSION.SDK_INT >= 33 -> getSerializable(key, m_class)
        else -> @Suppress("DEPRECATION") getSerializable(key) as? T
    }

inline fun <reified T : Serializable> Intent.serializable(key: String, m_class: Class<T>): T? =
    when {
        Build.VERSION.SDK_INT >= 33 -> getSerializableExtra(key, m_class)
        else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
    }

fun <T : Serializable?> getSerializable(intent: Intent, key: String, m_class: Class<T>): T? = try {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra(key, m_class)
    } else {
        intent.getSerializableExtra(key) as? T
    }
} catch (e: Exception) {
    e.printStackTrace()
    null
}


