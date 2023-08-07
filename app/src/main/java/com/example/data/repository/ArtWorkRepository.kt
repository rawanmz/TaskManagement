package com.example.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.data.remote.ArticApi
import com.example.model.UIState
import com.example.model.art.ArtWorksResponse
import javax.inject.Inject

class ArtWorkRepository @Inject constructor(private val articApi: ArticApi) : BaseRepository() {
    suspend fun getArtWorks(): UIState<ArtWorksResponse> {
        val response = articApi.getArtWorks()
        try {
            Log.d("apiiii", response.isSuccessful.toString())
            Log.d("apiiii", response.message())
            Log.d("apiiii", response.raw().request.url.toString())
            Log.d("apiiii", response.headers().toString())

            if (response.isSuccessful) {
                return UIState.Success(response.body()!!)
            } else {
                return UIState.Empty(message = response.errorBody().toString())
            }
        } catch (e: Exception) {
            return UIState.Error(e.message.toString())
        }
    }

        suspend fun getExhibitions(pageNumber: Int, pageLimitSize: Int) = safeApiCall {
        articApi.getExhibitions(
            pageNumber = pageNumber,
            pageLimitSize = pageLimitSize
        )
    }
//    fun getExhibitions(pageLimitSize: Int) = Pager(
//        config = PagingConfig(
//            pageSize = pageLimitSize,
//            prefetchDistance = 1, initialLoadSize = 2
//        ),
//        pagingSourceFactory = {
//            ExhibitionsPagingSource(articApi)
//        },
//        initialKey = 1
//    ).flow

    suspend fun getExhibitionById(id: Long) = safeApiCall {
        articApi.getExhibitionById("https://api.artic.edu/api/v1/exhibitions/$id")
    }
}