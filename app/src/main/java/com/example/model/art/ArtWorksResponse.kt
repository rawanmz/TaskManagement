package com.example.model.art

import com.google.gson.annotations.SerializedName

data class ArtWorksResponse(
    @SerializedName("pagination") var pagination: Pagination? = Pagination(),
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf(),
    @SerializedName("info") var info: Info? = Info(),
    @SerializedName("config") var config: Config? = Config()
)
