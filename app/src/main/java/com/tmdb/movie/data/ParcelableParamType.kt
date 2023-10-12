package com.tmdb.movie.data

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson

inline fun <reified T: Parcelable> createParcelableNavType(isNullableAllowed: Boolean = true): NavType<T> = object : NavType<T>(isNullableAllowed) {
    override fun parseValue(value: String): T {
        return Gson().fromJson(value, T::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }

    @Suppress("DEPRECATION")
    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getParcelable(key)
    }
}