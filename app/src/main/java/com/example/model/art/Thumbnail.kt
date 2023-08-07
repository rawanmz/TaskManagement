package com.example.model.art

import com.google.gson.annotations.SerializedName

data class Thumbnail (

    @SerializedName("lqip"     ) var lqip    : String? = null,
    @SerializedName("width"    ) var width   : Int?    = null,
    @SerializedName("height"   ) var height  : Int?    = null,
    @SerializedName("alt_text" ) var altText : String? = null

)