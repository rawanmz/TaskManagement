package com.example.model.exhibition

import com.google.gson.annotations.SerializedName

data class ExhibitionResponse(

    @SerializedName("pagination") var pagination: ExhibitionPagination? = ExhibitionPagination(),
    @SerializedName("data") var data: ArrayList<ExhibitionData> = arrayListOf(),
    @SerializedName("info") var info: ExhibitionInfo? = ExhibitionInfo(),
    @SerializedName("config") var config: ExhibitionConfig? = ExhibitionConfig()
)

data class ExhibitionByIdResponse(

    @SerializedName("data") var data: ExhibitionData? = ExhibitionData(),
    @SerializedName("info") var info: ExhibitionInfo? = ExhibitionInfo(),
    @SerializedName("config") var config: ExhibitionConfig? = ExhibitionConfig()
)