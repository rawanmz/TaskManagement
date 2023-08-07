package com.example.model.art

import com.google.gson.annotations.SerializedName

data class Contexts (

    @SerializedName("groupings" ) var groupings : ArrayList<String> = arrayListOf()

)