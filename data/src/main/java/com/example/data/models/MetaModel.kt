package com.example.data.models

import com.google.gson.annotations.SerializedName

// Pagination information
data class MetaModel(
    @SerializedName("page") val page: Int,
    @SerializedName("has_next") val hasNext: Boolean,
    @SerializedName("has_previous") val hasPrevious: Boolean
)