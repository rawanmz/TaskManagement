package com.example.model.exhibition

import com.google.gson.annotations.SerializedName

data class ExhibitionSuggestAutocompleteAll(
    @SerializedName("input") var input: ArrayList<String> = arrayListOf(),
    @SerializedName("contexts") var contexts: ExhibitionGroupings? = ExhibitionGroupings()

)
