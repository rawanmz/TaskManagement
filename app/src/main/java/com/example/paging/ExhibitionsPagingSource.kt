package com.example.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.remote.ArticApi
import com.example.data.repository.ArtWorkRepository
import com.example.model.exhibition.ExhibitionData
import com.example.model.exhibition.ExhibitionResponse
import javax.inject.Inject

class ExhibitionsPagingSource(private val artWorkRepository: ArtWorkRepository) :
    PagingSource<Int, ExhibitionData>() {
    override fun getRefreshKey(state: PagingState<Int, ExhibitionData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
           val page= state.closestPageToPosition(anchorPosition)
            page?.prevKey?.minus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ExhibitionData> {
        return try {
            val page = params.key ?: 1
            val response = artWorkRepository.getExhibitions(pageNumber = page, pageLimitSize = 50)
            LoadResult.Page(
                data = response.responseData?.data.orEmpty(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.responseData?.pagination?.nextUrl==null) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
