package com.tmdb.movie.repository

import android.util.Log
import androidx.datastore.core.DataStore
import com.tmdb.movie.data.AccountState
import com.tmdb.movie.data.CreateListParam
import com.tmdb.movie.data.FavoriteRequest
import com.tmdb.movie.data.HomePopularMovie
import com.tmdb.movie.data.ImagesData
import com.tmdb.movie.data.ListsDetail
import com.tmdb.movie.data.MediaIdRequest
import com.tmdb.movie.data.MediaItem
import com.tmdb.movie.data.MediaList
import com.tmdb.movie.data.MediaType
import com.tmdb.movie.data.MovieDetails
import com.tmdb.movie.data.People
import com.tmdb.movie.data.PeopleCredits
import com.tmdb.movie.data.PeopleDetails
import com.tmdb.movie.data.RequestToken
import com.tmdb.movie.data.RequestTokenInfo
import com.tmdb.movie.data.ResponseResult
import com.tmdb.movie.data.Result
import com.tmdb.movie.data.Session
import com.tmdb.movie.data.TMDBConfig
import com.tmdb.movie.data.TMDBConfiguration
import com.tmdb.movie.data.UserData
import com.tmdb.movie.data.WatchlistRequest
import com.tmdb.movie.data.asResult
import com.tmdb.movie.db.PopularMovieDao
import com.tmdb.movie.network.ApiService
import com.tmdb.movie.paging.AccountMediaListsPagingSource
import com.tmdb.movie.paging.DiscoveryMoviePagingSource
import com.tmdb.movie.paging.MyMediaListPagingSource
import com.tmdb.movie.paging.SearchMoviePagingSource
import com.tmdb.movie.utils.BASE_TMDB_IMAGE_URL
import com.tmdb.movie.utils.DEFAULT_IMAGE_SIZE
import com.tmdb.movie.utils.formatLongToString
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class TMDBMovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: PopularMovieDao,
    private val dataStore: DataStore<TMDBConfig>,
) : IMovieRepository {

    override val configStream: Flow<TMDBConfig>
        get() = dataStore.data.map { config ->
            val updateTime = config.updateTime
            val currentTime = formatLongToString(System.currentTimeMillis(), "yyyy-MM-dd")
            // 每天只从服务器上更新一次配置信息
            if (updateTime == null || updateTime != currentTime) {
                getTMDBConfiguration().let { newConfig ->
                    dataStore.updateData {
                        it.copy(
                            baseImageUrl = newConfig.images?.secureBaseUrl ?: BASE_TMDB_IMAGE_URL,
                            backdropSizeList = newConfig.images?.backdropSizes,
                            logoSizeList = newConfig.images?.logoSizes,
                            posterSizeList = newConfig.images?.posterSizes,
                            profileSizeList = newConfig.images?.profileSizes,
                            stillSizeList = newConfig.images?.stillSizes,
                            updateTime = currentTime
                        )
                    }
                }
            }
            config
        }
            .catch {
                emit(
                    TMDBConfig(
                        baseImageUrl = BASE_TMDB_IMAGE_URL,
                        backdropSizeList = listOf(DEFAULT_IMAGE_SIZE),
                        logoSizeList = listOf(DEFAULT_IMAGE_SIZE),
                        posterSizeList = listOf(DEFAULT_IMAGE_SIZE),
                        profileSizeList = listOf(DEFAULT_IMAGE_SIZE),
                        stillSizeList = listOf(DEFAULT_IMAGE_SIZE),
                        updateTime = null
                    )
                )
            }

    override suspend fun updateDarkThemeType(themeType: Int) {
        dataStore.updateData { currentThemeConfig ->
            currentThemeConfig.copy(darkTheme = themeType)
        }
    }

    override suspend fun updateDynamicTheme(useDynamicTheme: Boolean) {
        dataStore.updateData { currentThemeConfig ->
            currentThemeConfig.copy(useDynamicTheme = useDynamicTheme)
        }
    }

    override suspend fun getTMDBConfiguration(): TMDBConfiguration {
        return apiService.getConfiguration()
    }

    override fun getMoviesTrending(): Flow<Result<List<MediaItem>>> = flow {
        Log.w("sqsong", "Get Trending movies.")
        emit(apiService.getMoviesTrending())
    }.map { it.results ?: emptyList() }
        .asResult()

    override fun getTVTrending(): Flow<Result<List<MediaItem>>> = flow {
        Log.w("sqsong", "Get Trending TV.")
        emit(apiService.getTVTrending())
    }.map { it.results ?: emptyList() }
        .asResult()

    override fun getPeopleTrending(): Flow<Result<List<People>>> = flow {
        Log.w("sqsong", "Get Trending People.")
        emit(apiService.getPeopleTrending())
    }.map { it.results ?: emptyList() }
        .asResult()

    override fun getPopularPeople(): Flow<Result<List<People>>> = flow {
        Log.w("sqsong", "Get Popular People.")
        emit(apiService.getPopularPeople())
    }.map { it.results ?: emptyList() }
        .asResult()

    override fun getMovieNowPlaying(page: Int): Flow<Result<List<MediaItem>>> = flow {
        Log.w("sqsong", "Get now playing movies.")
        emit(apiService.getMoviesNowPlaying())
    }.map { it.results ?: emptyList() }
        .asResult()

    override fun getTVAiringToday(page: Int): Flow<Result<List<MediaItem>>> = flow {
        Log.w("sqsong", "Get airing day TV.")
        emit(apiService.getTVAiringToday())
    }.map { it.results ?: emptyList() }
        .asResult()

    override fun getMoviePopular(page: Int): Flow<Result<List<MediaItem>>> = flow {
        Log.w("sqsong", "Get popular movies.")
        val movieList = apiService.getMoviesPopular().results
        movieList?.let {
            val firstMovie = movieDao.getFirstPopularMovie()
            val currentDate = formatLongToString(System.currentTimeMillis())
            if (firstMovie == null || firstMovie.updatedAt != currentDate) {
                movieDao.deleteAllPopularMovies()
                movieDao.insertPopularMovies(movieList.map { it.toHomePopularMovie() })
            }
            Log.e("sqsong", "firstMovie: $firstMovie")
        }
        emit(movieList ?: emptyList())
    }.asResult()

    override fun getTVPopular(page: Int): Flow<Result<List<MediaItem>>> = flow {
        Log.w("sqsong", "Get popular TV.")
        emit(apiService.getTVPopular())
    }.map { it.results ?: emptyList() }
        .asResult()

    override fun getDiscoveryMoviePagingSource(discoveryType: Int): DiscoveryMoviePagingSource = DiscoveryMoviePagingSource(apiService, discoveryType)

    override fun getMovieDetails(movieId: Int): Flow<Result<MovieDetails>> = flow {
        delay(1000)
        emit(apiService.getMovieDetails(movieId))
    }.asResult()

    override fun getTVDetails(id: Int): Flow<Result<MovieDetails>> = flow {
        delay(1000)
        emit(apiService.getTVDetails(id))
    }.asResult()

    override fun getMovieImages(id: Int): Flow<Result<ImagesData>> {
        return flow {
            emit(apiService.getMovieImages(id))
        }.asResult()
    }

    override fun getTVImages(id: Int): Flow<Result<ImagesData>> = flow {
        emit(apiService.getTVImages(id))
    }.asResult()

    override fun getPeopleDetails(id: Int): Flow<Result<PeopleDetails>> = flow {
        delay(1000)
        emit(apiService.getPeopleDetails(id))
    }.asResult()

    override fun getPeopleCredits(id: Int): Flow<Result<PeopleCredits>> = flow {
        emit(apiService.getPeopleMovieCredits(id))
    }.zip(flow {
        emit(apiService.getPeopleTvCredits(id))
    }) { movieCredits, tvCredits ->
        val mCasts = movieCredits.cast?.map { it.copy(mediaType = "movie") } ?: emptyList()
        val tCasts = tvCredits.cast?.map { it.copy(mediaType = "tv") } ?: emptyList()
        val mCrews = movieCredits.crew?.map { it.copy(mediaType = "movie") } ?: emptyList()
        val tCrews = tvCredits.crew?.map { it.copy(mediaType = "tv") } ?: emptyList()
        PeopleCredits(mCasts + tCasts, mCrews + tCrews, id)
    }.asResult()

    override fun getSearchMoviePagingSource(query: String): SearchMoviePagingSource {
        return SearchMoviePagingSource(apiService, query)
    }

    override fun getRequestToken(): Flow<Result<RequestTokenInfo>> = flow {
        emit(apiService.getRequestToken())
    }.asResult()

    override fun refreshUserData(requestToken: String): Flow<Result<Boolean>> = flow {
        val sessionData = apiService.createSession(RequestToken(requestToken))
        if (!sessionData.success || sessionData.sessionId.isNullOrEmpty()) {
            throw Exception("Create session error: ${sessionData.statusMessage}.")
        }
        apiService.getUserData(sessionData.sessionId).let { userData ->
            dataStore.updateData {
                it.copy(
                    userData = userData.copy(sessionId = sessionData.sessionId)
                )
            }
        }
        emit(true)
    }.asResult()

    override fun signOut(sessionId: String): Flow<Result<Boolean>> = flow {
        val result = apiService.deleteSession(Session(sessionId))
        if (!result.success) {
            throw Exception("Delete session error: ${result.statusMessage}.")
        }
        dataStore.updateData {
            it.copy(
                userData = null
            )
        }
        emit(true)
    }.asResult()

    override fun getAccountState(id: Int, sessionId: String, mediaType: Int): Flow<Result<AccountState>> = flow {
        val accountState = if (mediaType == MediaType.MOVIE) {
            apiService.getMovieAccountState(id, sessionId)
        } else {
            apiService.getTVAccountState(id, sessionId)
        }
        emit(accountState)
    }.asResult()

    override fun markAsFavorite(accountId: Int, sessionId: String, mediaType: Int, mediaId: Int, favorite: Boolean): Flow<Result<Boolean>> =
        flow {
            val newMediaType = if (mediaType == MediaType.MOVIE) "movie" else "tv"
            val request = FavoriteRequest(mediaType = newMediaType, mediaId = mediaId, favorite = favorite)
            val result = apiService.addToFavorite(accountId, request)
            if (!result.success) throw Exception("Change favorite state error: ${result.statusMessage}.")
            emit(favorite)
        }.asResult()

    override fun addToWatchlist(accountId: Int, sessionId: String, movieType: Int, mediaId: Int, watchlist: Boolean): Flow<Result<Boolean>> =
        flow {
            val newMediaType = if (movieType == MediaType.MOVIE) "movie" else "tv"
            val request = WatchlistRequest(mediaType = newMediaType, mediaId = mediaId, watchlist = watchlist)
            val result = apiService.addToWatchlist(accountId, request)
            if (!result.success) throw Exception("Change watchlist state error: ${result.statusMessage}.")
            emit(watchlist)
        }.asResult()

    override fun getAccountMediaLists(accountId: Int): Flow<Result<List<MediaList>?>> = flow {
        val mediaLists = apiService.getAccountMediaLists(accountId)
        emit(mediaLists.results)
    }.asResult()

    override fun addMediaToList(sessionId: String, mediaId: Int, listId: Int, mediaType: String): Flow<Result<ResponseResult>> {
        return flow {
            emit(apiService.addMediaToList(listId, sessionId, MediaIdRequest(mediaId, mediaType)))
        }.asResult()
    }

    override fun createList(sessionId: String, param: CreateListParam): Flow<Result<Boolean>> {
        return flow {
            val result = apiService.createList(sessionId, param)
            emit(result.success)
        }.asResult()
    }

    override fun getHomePopularMovie(): Flow<Result<List<HomePopularMovie>>> = movieDao.getPopularMovies().asResult()

    override fun getAccountListsPagingSource(accountId: Int): MyMediaListPagingSource = MyMediaListPagingSource(accountId, apiService)

    override fun getListsDetail(listId: Int): Flow<Result<ListsDetail>> = flow {
        emit(apiService.getListsDetail(listId))
    }.asResult()

    override suspend fun updateUserData(sessionId: String): UserData {
        val userData = apiService.getUserData(sessionId)
        dataStore.updateData {
            it.copy(userData = userData.copy(sessionId = sessionId))
        }
        return userData
    }

    override fun getAccountMediaListsPagingSource(accountId: Int, mediaType: Int, accountMediaType: Int): AccountMediaListsPagingSource {
        return AccountMediaListsPagingSource(apiService, accountId, mediaType, accountMediaType)
    }
}