package com.example.model.art

import com.google.gson.annotations.SerializedName

data class SuggestAutocompleteAll (

    @SerializedName("input"    ) var input    : ArrayList<String> = arrayListOf(),
    @SerializedName("contexts" ) var contexts : Contexts?         = Contexts()

)