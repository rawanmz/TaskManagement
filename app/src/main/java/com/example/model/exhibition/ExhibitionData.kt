package com.example.model.exhibition

import com.google.gson.annotations.SerializedName

data class ExhibitionData(

    @SerializedName("id") var id: Long? = null,
    @SerializedName("api_model") var apiModel: String? = null,
    @SerializedName("api_link") var apiLink: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("is_featured") var isFeatured: Boolean? = null,
    @SerializedName("short_description") var shortDescription: String? = null,
    @SerializedName("web_url") var webUrl: String? = null,
    @SerializedName("image_url") var imageUrl: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("aic_start_at") var aicStartAt: String? = null,
    @SerializedName("aic_end_at") var aicEndAt: String? = null,
    @SerializedName("gallery_id") var galleryId: Int? = null,
    @SerializedName("gallery_title") var galleryTitle: String? = null,
    @SerializedName("artwork_ids") var artworkIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("artwork_titles") var artworkTitles: ArrayList<String> = arrayListOf(),
    @SerializedName("artist_ids") var artistIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("site_ids") var siteIds: ArrayList<String> = arrayListOf(),
    @SerializedName("image_id") var imageId: String? = null,
    @SerializedName("alt_image_ids") var altImageIds: ArrayList<String> = arrayListOf(),
    @SerializedName("document_ids") var documentIds: ArrayList<String> = arrayListOf(),
    @SerializedName("suggest_autocomplete_boosted") var suggestAutocompleteBoosted: String? = null,
    @SerializedName("suggest_autocomplete_all") var suggestAutocompleteAll: ExhibitionSuggestAutocompleteAll? = ExhibitionSuggestAutocompleteAll(),
    @SerializedName("source_updated_at") var sourceUpdatedAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("timestamp") var timestamp: String? = null,
    @SerializedName("iiif_url") var iiif_url: String? = null,


    )