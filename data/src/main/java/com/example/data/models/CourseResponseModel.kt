package com.example.data.models

import com.google.gson.annotations.SerializedName

data class CourseResponseModel(
    @SerializedName("meta") val meta: MetaModel,
    @SerializedName("courses") val courses: List<CourseModel>
)