package com.example.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("summary") val summary: String?,
    @SerializedName("learners_count") val learners: Int,
    @SerializedName("display_price") val price: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("cover") val cover: String?,
    @SerializedName("authors") val authors: List<Int?>?,
    @SerializedName("update_date") val date: String?,
    @SerializedName("difficulty") val difficulty: String?,
    @SerializedName("course_type") val type: String?,
    @SerializedName("is_favorite") var favorite: Boolean,
): Parcelable
