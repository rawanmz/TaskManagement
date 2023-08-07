package com.example.model.exhibition

import com.google.gson.annotations.SerializedName

data class ExhibitionConfig(
    @SerializedName("iiif_url") var iiifUrl: String? = null,
    @SerializedName("website_url") var websiteUrl: String? = null
)