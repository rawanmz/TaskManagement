package com.example.model.art

import com.google.gson.annotations.SerializedName

data class Info (

    @SerializedName("license_text"  ) var licenseText  : String?           = null,
    @SerializedName("license_links" ) var licenseLinks : ArrayList<String> = arrayListOf(),
    @SerializedName("version"       ) var version      : String?           = null

)