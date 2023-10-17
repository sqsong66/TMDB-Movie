package com.tmdb.movie.network

import android.content.Context
import com.google.gson.Gson
import com.tmdb.movie.R
import com.tmdb.movie.data.BaseErrorData
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import okio.GzipSource
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class TMDBNetworkInterceptor(
    private val context: Context,
    private val gson: Gson
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val apiKey = context.resources.getString(R.string.tmdb_access_token)
        if (apiKey.isNotEmpty()) {
            builder.addHeader("Authorization", "Bearer $apiKey")
        }
        val response = chain.proceed(builder.build())
        if (!response.isSuccessful) {
            val source = response.body?.source()
            source?.request(Long.MAX_VALUE) // Buffer the entire body.
            var buffer = source?.buffer!!
            if ("gzip".equals(response.headers["Content-Encoding"], ignoreCase = true)) {
                GzipSource(buffer.clone()).use { gzippedResponseBody ->
                    buffer = Buffer()
                    buffer.writeAll(gzippedResponseBody)
                }
            }
            val contentType = response.body?.contentType()
            val charset: Charset = contentType?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8
            buffer.clone().readString(charset)
            val errorStr = buffer.clone().readString(charset)
            if (errorStr.isNotEmpty()) {
                gson.fromJson(errorStr, BaseErrorData::class.java)?.let {
                    throw TMDBNetworkException(response.code, it.statusCode, it.statusMessage)
                }
            }
            throw TMDBNetworkException(response.code, -1, errorStr)
        }
        return response
    }
}