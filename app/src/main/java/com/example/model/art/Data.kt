package com.example.model.art

import com.google.gson.annotations.SerializedName

data class Data(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("api_model") var apiModel: String? = null,
    @SerializedName("api_link") var apiLink: String? = null,
    @SerializedName("is_boosted") var isBoosted: Boolean? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("alt_titles") var altTitles: List<String>? = null,
    @SerializedName("thumbnail") var thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("main_reference_number") var mainReferenceNumber: String? = null,
    @SerializedName("has_not_been_viewed_much") var hasNotBeenViewedMuch: Boolean? = null,
    @SerializedName("boost_rank") var boostRank: String? = null,
    @SerializedName("date_start") var dateStart: Int? = null,
    @SerializedName("date_end") var dateEnd: Int? = null,
    @SerializedName("date_display") var dateDisplay: String? = null,
    @SerializedName("date_qualifier_title") var dateQualifierTitle: String? = null,
    @SerializedName("date_qualifier_id") var dateQualifierId: String? = null,
    @SerializedName("artist_display") var artistDisplay: String? = null,
    @SerializedName("place_of_origin") var placeOfOrigin: String? = null,
    @SerializedName("dimensions") var dimensions: String? = null,
    @SerializedName("medium_display") var mediumDisplay: String? = null,
    @SerializedName("inscriptions") var inscriptions: String? = null,
    @SerializedName("credit_line") var creditLine: String? = null,
    @SerializedName("catalogue_display") var catalogueDisplay: String? = null,
    @SerializedName("publication_history") var publicationHistory: String? = null,
    @SerializedName("exhibition_history") var exhibitionHistory: String? = null,
    @SerializedName("provenance_text") var provenanceText: String? = null,
    @SerializedName("publishing_verification_level") var publishingVerificationLevel: String? = null,
    @SerializedName("internal_department_id") var internalDepartmentId: Int? = null,
    @SerializedName("fiscal_year") var fiscalYear: String? = null,
    @SerializedName("fiscal_year_deaccession") var fiscalYearDeaccession: String? = null,
    @SerializedName("is_public_domain") var isPublicDomain: Boolean? = null,
    @SerializedName("is_zoomable") var isZoomable: Boolean? = null,
    @SerializedName("max_zoom_window_size") var maxZoomWindowSize: Int? = null,
    @SerializedName("copyright_notice") var copyrightNotice: String? = null,
    @SerializedName("has_multimedia_resources") var hasMultimediaResources: Boolean? = null,
    @SerializedName("has_educational_resources") var hasEducationalResources: Boolean? = null,
    @SerializedName("has_advanced_imaging") var hasAdvancedImaging: Boolean? = null,
    @SerializedName("colorfulness") var colorfulness: Double? = null,
//    @SerializedName("color"                         ) var color                       : String?                           = null,
    @SerializedName("latitude") var latitude: String? = null,
    @SerializedName("longitude") var longitude: String? = null,
    @SerializedName("latlon") var latlon: String? = null,
    @SerializedName("is_on_view") var isOnView: Boolean? = null,
    @SerializedName("on_loan_display") var onLoanDisplay: String? = null,
    @SerializedName("gallery_title") var galleryTitle: String? = null,
    @SerializedName("gallery_id") var galleryId: String? = null,
    @SerializedName("artwork_type_title") var artworkTypeTitle: String? = null,
    @SerializedName("artwork_type_id") var artworkTypeId: Int? = null,
    @SerializedName("department_title") var departmentTitle: String? = null,
    @SerializedName("department_id") var departmentId: String? = null,
    @SerializedName("artist_id") var artistId: Int? = null,
    @SerializedName("artist_title") var artistTitle: String? = null,
    @SerializedName("alt_artist_ids") var altArtistIds: ArrayList<String> = arrayListOf(),
    @SerializedName("artist_ids") var artistIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("artist_titles") var artistTitles: ArrayList<String> = arrayListOf(),
    @SerializedName("category_ids") var categoryIds: ArrayList<String> = arrayListOf(),
    @SerializedName("category_titles") var categoryTitles: ArrayList<String> = arrayListOf(),
    @SerializedName("term_titles") var termTitles: ArrayList<String> = arrayListOf(),
    @SerializedName("style_id") var styleId: String? = null,
    @SerializedName("style_title") var styleTitle: String? = null,
    @SerializedName("alt_style_ids") var altStyleIds: ArrayList<String> = arrayListOf(),
    @SerializedName("style_ids") var styleIds: ArrayList<String> = arrayListOf(),
    @SerializedName("style_titles") var styleTitles: ArrayList<String> = arrayListOf(),
    @SerializedName("classification_id") var classificationId: String? = null,
    @SerializedName("classification_title") var classificationTitle: String? = null,
    @SerializedName("alt_classification_ids") var altClassificationIds: ArrayList<String> = arrayListOf(),
    @SerializedName("classification_ids") var classificationIds: ArrayList<String> = arrayListOf(),
    @SerializedName("classification_titles") var classificationTitles: ArrayList<String> = arrayListOf(),
    @SerializedName("subject_id") var subjectId: String? = null,
    @SerializedName("alt_subject_ids") var altSubjectIds: ArrayList<String> = arrayListOf(),
    @SerializedName("subject_ids") var subjectIds: ArrayList<String> = arrayListOf(),
    @SerializedName("subject_titles") var subjectTitles: ArrayList<String> = arrayListOf(),
    @SerializedName("material_id") var materialId: String? = null,
    @SerializedName("alt_material_ids") var altMaterialIds: ArrayList<String> = arrayListOf(),
    @SerializedName("material_ids") var materialIds: ArrayList<String> = arrayListOf(),
    @SerializedName("material_titles") var materialTitles: ArrayList<String> = arrayListOf(),
    @SerializedName("technique_id") var techniqueId: String? = null,
    @SerializedName("alt_technique_ids") var altTechniqueIds: ArrayList<String> = arrayListOf(),
    @SerializedName("technique_ids") var techniqueIds: ArrayList<String> = arrayListOf(),
    @SerializedName("technique_titles") var techniqueTitles: ArrayList<String> = arrayListOf(),
    @SerializedName("theme_titles") var themeTitles: ArrayList<String> = arrayListOf(),
    @SerializedName("image_id") var imageId: String? = null,
    @SerializedName("alt_image_ids") var altImageIds: ArrayList<String> = arrayListOf(),
    @SerializedName("document_ids") var documentIds: ArrayList<String> = arrayListOf(),
    @SerializedName("sound_ids") var soundIds: ArrayList<String> = arrayListOf(),
    @SerializedName("video_ids") var videoIds: ArrayList<String> = arrayListOf(),
    @SerializedName("text_ids") var textIds: ArrayList<String> = arrayListOf(),
//    @SerializedName("section_ids"                   ) var sectionIds                  : ArrayList<Int>                    = arrayListOf(),
    @SerializedName("section_titles") var sectionTitles: ArrayList<String> = arrayListOf(),
    @SerializedName("site_ids") var siteIds: ArrayList<String> = arrayListOf(),
    @SerializedName("suggest_autocomplete_all") var suggestAutocompleteAll: ArrayList<SuggestAutocompleteAll> = arrayListOf(),
    @SerializedName("source_updated_at") var sourceUpdatedAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("timestamp") var timestamp: String? = null

)