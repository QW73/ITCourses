package com.example.domain.repositories

import com.example.data.models.CourseModel
import com.example.data.models.CourseResponseModel

interface CourseRepository {
    suspend fun getCoursesOnPage(
        page: Int,
        price: String? = null,
        type: String? = null,
        difficulty: String? = null
    ): CourseResponseModel
}
