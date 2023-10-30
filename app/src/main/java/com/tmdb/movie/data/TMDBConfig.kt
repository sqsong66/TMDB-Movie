package com.tmdb.movie.data

import android.content.Context
import androidx.annotation.IntDef
import com.tmdb.movie.R
import com.tmdb.movie.utils.DEFAULT_IMAGE_SIZE
import kotlinx.serialization.Serializable

@IntDef(DarkThemeType.FOLLOW_SYSTEM, DarkThemeType.DARK, DarkThemeType.LIGHT)
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
annotation class DarkThemeType {
    companion object {
        const val FOLLOW_SYSTEM = 0
        const val DARK = 1
        const val LIGHT = 2
    }
}

@IntDef(ImageType.BACKDROP, ImageType.LOGO, ImageType.POSTER, ImageType.PROFILE, ImageType.STILL)
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
annotation class ImageType {
    companion object {
        const val BACKDROP = 0
        const val LOGO = 1
        const val POSTER = 2
        const val PROFILE = 3
        const val STILL = 4
    }
}

@IntDef(ImageSize.SMALL, ImageSize.MEDIUM, ImageSize.LARGE)
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
annotation class ImageSize {
    companion object {
        const val SMALL = 0
        const val MEDIUM = 1
        const val LARGE = 2
    }
}

@Serializable
data class TMDBConfig(
    @DarkThemeType
    val darkTheme: Int = DarkThemeType.FOLLOW_SYSTEM,
    val useDynamicTheme: Boolean = false,
    val baseImageUrl: String? = null,
    val backdropSizeList: List<String>? = null,
    val logoSizeList: List<String>? = null,
    val posterSizeList: List<String>? = null,
    val profileSizeList: List<String>? = null,
    val stillSizeList: List<String>? = null,
    val updateTime: String? = null,
    val userData: UserData? = null,
) {

    fun buildImageUrl(imagePath: String?, @ImageType imageType: Int, @ImageSize imageSize: Int = ImageSize.MEDIUM): String {
        return if (imagePath.isNullOrEmpty()) {
            ""
        } else {
            val baseUrl = baseImageUrl ?: return ""
            val resultSize = when (imageType) {
                ImageType.BACKDROP -> {
                    when (imageSize) {
                        ImageSize.SMALL -> {
                            backdropSizeList?.getOrNull(0) ?: DEFAULT_IMAGE_SIZE
                        }

                        ImageSize.MEDIUM -> {
                            backdropSizeList?.getOrNull(backdropSizeList.size - 2) ?: DEFAULT_IMAGE_SIZE
                        }

                        else -> {
                            backdropSizeList?.lastOrNull() ?: DEFAULT_IMAGE_SIZE
                        }
                    }
                }

                ImageType.LOGO -> {
                    when (imageSize) {
                        ImageSize.SMALL -> {
                            logoSizeList?.getOrNull(0) ?: DEFAULT_IMAGE_SIZE
                        }

                        ImageSize.MEDIUM -> {
                            logoSizeList?.getOrNull(logoSizeList.size - 2) ?: DEFAULT_IMAGE_SIZE
                        }

                        else -> {
                            logoSizeList?.lastOrNull() ?: DEFAULT_IMAGE_SIZE
                        }
                    }
                }

                ImageType.POSTER -> {
                    when (imageSize) {
                        ImageSize.SMALL -> {
                            posterSizeList?.getOrNull(0) ?: DEFAULT_IMAGE_SIZE
                        }

                        ImageSize.MEDIUM -> {
                            posterSizeList?.getOrNull(posterSizeList.size - 2) ?: DEFAULT_IMAGE_SIZE
                        }

                        else -> {
                            posterSizeList?.lastOrNull() ?: DEFAULT_IMAGE_SIZE
                        }
                    }
                }

                ImageType.PROFILE -> {
                    when (imageSize) {
                        ImageSize.SMALL -> {
                            profileSizeList?.getOrNull(0) ?: DEFAULT_IMAGE_SIZE
                        }

                        ImageSize.MEDIUM -> {
                            profileSizeList?.getOrNull(profileSizeList.size - 2) ?: DEFAULT_IMAGE_SIZE
                        }

                        else -> {
                            profileSizeList?.lastOrNull() ?: DEFAULT_IMAGE_SIZE
                        }
                    }
                }

                ImageType.STILL -> {
                    when (imageSize) {
                        ImageSize.SMALL -> {
                            stillSizeList?.getOrNull(0) ?: DEFAULT_IMAGE_SIZE
                        }

                        ImageSize.MEDIUM -> {
                            stillSizeList?.getOrNull(stillSizeList.size - 2) ?: DEFAULT_IMAGE_SIZE
                        }

                        else -> {
                            stillSizeList?.lastOrNull() ?: DEFAULT_IMAGE_SIZE
                        }
                    }
                }

                else -> {
                    return ""
                }
            }
            "$baseUrl$resultSize$imagePath"
        }
    }

    fun buildAvatarUrl(context: Context, isSmall: Boolean = false): String {
        return userData?.let {
            val profileUrl = it.avatar?.tmdb?.avatarPath
            if (!profileUrl.isNullOrEmpty()) {
                if (isSmall) {
                    buildImageUrl(profileUrl, ImageType.PROFILE, ImageSize.SMALL)
                } else {
                    buildImageUrl(profileUrl, ImageType.PROFILE, ImageSize.LARGE)
                }
            } else {
                val gravatarHash = it.avatar?.gravatar?.hash
                String.format(context.getString(if (isSmall) R.string.key_gravatar_url_small else R.string.key_gravatar_url), gravatarHash)
            }
        } ?: ""
    }

    fun isEmptyAvatar(): Boolean {
        return userData?.avatar?.tmdb?.avatarPath.isNullOrEmpty() && userData?.avatar?.gravatar?.hash.isNullOrEmpty()
    }

    fun isLogin(): Boolean {
        return userData != null
    }

    override fun toString(): String {
        return "TMDBConfig(darkTheme=$darkTheme, useDynamicTheme=$useDynamicTheme, baseImageUrl=$baseImageUrl, backdropSizeList=$backdropSizeList, logoSizeList=$logoSizeList, posterSizeList=$posterSizeList, profileSizeList=$profileSizeList, stillSizeList=$stillSizeList, updateTime=$updateTime, userData=$userData)"
    }
}

