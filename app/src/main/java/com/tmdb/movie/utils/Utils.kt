package com.tmdb.movie.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.tmdb.movie.data.MediaType
import java.util.Locale


fun getLanguage(): String {
    val language = Locale.getDefault().language
    return if (language == "zh" && Locale.getDefault().country.equals("CN", true)) {
        "zh"
    } else {
        "en"
    }
}

fun playMediaVideo(context: Context, videoKey: String?, isYouTuBe: Boolean = true) {
    if (videoKey.isNullOrEmpty()) return
    if (isYouTuBe) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoKey"))
        try {
            context.startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            launchUrl(context = context, url = "http://www.youtube.com/watch?v=$videoKey")
        }
    } else {
        launchUrl(context = context, url = "https://vimeo.com/$videoKey")
    }

}

fun launchUrl(context: Context, url: String?) {
    if (url.isNullOrEmpty()) return
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

fun copyTextToClipboard(context: Context, text: String?) {
    if (text.isNullOrEmpty()) return
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
    val clip = android.content.ClipData.newPlainText("TMDB", text)
    clipboard.setPrimaryClip(clip)
}

fun shareLink(context: Context, link: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, link)
    context.startActivity(Intent.createChooser(intent, "Share link"))
}

fun shareTMDBMedia(context: Context, mediaId: Int, @MediaType mediaType: Int) {
    val shareTypeStr: String = when (mediaType) {
        MediaType.MOVIE -> {
            "movie"
        }

        MediaType.TV -> {
            "tv"
        }

        else -> {
            "person"
        }
    }
    val link = "https://www.themoviedb.org/$shareTypeStr/$mediaId"
    shareLink(context, link)
}