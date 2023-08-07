package com.example.model.exhibition

import com.google.gson.annotations.SerializedName

data class ExhibitionGroupings(
    @SerializedName("groupings") var groupings: ArrayList<String> = arrayListOf()
)
