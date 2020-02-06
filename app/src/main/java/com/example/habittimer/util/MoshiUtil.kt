package com.example.habittimer.util

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.*


object MoshiUtil{
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory())
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()

    fun getInstance(): Moshi = moshi

    fun <T: Any, C: Class<T>> serializeListToJson(value: List<T>, from: C): String? {
        val types = Types.newParameterizedType(List::class.java, from)
        val adapter = moshi.adapter<List<T>>(types)
        try {
            val json = adapter.toJson(value)
            return json
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    fun <T: Any, C: Class<T>> deserializeAsList(json: String, to: C): List<T>? {
        val types = Types.newParameterizedType(List::class.java, to)
        val adapter = moshi.adapter<List<T>>(types).nullSafe()
        try {
            return adapter.fromJson(json)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}