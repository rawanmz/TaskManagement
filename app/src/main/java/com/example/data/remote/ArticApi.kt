package com.example.data.remote

import com.example.model.art.ArtWorksResponse
import com.example.model.exhibition.ExhibitionByIdResponse
import com.example.model.exhibition.ExhibitionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ArticApi {
    @GET
    suspend fun getArtWorks(@Url url: String = "https://api.artic.edu/api/v1/artworks"): Response<ArtWorksResponse>

    @GET
    suspend fun getExhibitions(
        @Url url: String = "https://api.artic.edu/api/v1/exhibitions",
        @Query("page") pageNumber: Int,
        @Query("limit") pageLimitSize: Int,
    ): Response<ExhibitionResponse>

    @GET
    suspend fun getExhibitionById(
        @Url url: String,
//        @Path("id") id: Long,
    ): Response<ExhibitionByIdResponse>
}