package com.tmdb.movie.datastore

import android.os.Build
import androidx.datastore.core.Serializer
import com.tmdb.movie.data.TMDBConfig
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class ThemeConfigSerializer @Inject constructor() : Serializer<TMDBConfig> {
    override val defaultValue: TMDBConfig
        get() = TMDBConfig(useDynamicTheme = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)

    override suspend fun readFrom(input: InputStream): TMDBConfig {
        return Json.decodeFromString(TMDBConfig.serializer(), input.readBytes().decodeToString())
    }

    override suspend fun writeTo(t: TMDBConfig, output: OutputStream) {
        output.write(Json.encodeToString(TMDBConfig.serializer(), t).toByteArray())
    }
}