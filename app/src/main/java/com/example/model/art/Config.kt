package com.example.model.art

import com.google.gson.annotations.SerializedName

data class Config (

    @SerializedName("iiif_url"    ) var iiifUrl    : String? = null,
    @SerializedName("website_url" ) var websiteUrl : String? = null

)