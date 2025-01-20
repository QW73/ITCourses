package com.example.domain.repositories

import com.example.data.models.CourseModel
import com.example.data.models.CourseResponseModel
import com.example.data.network.ApiService

class CourseRepositoryImpl(private val apiService: ApiService) : CourseRepository {

    override suspend fun getCoursesOnPage(
        page: Int, price: String?, type: String?, difficulty: String?
    ): CourseResponseModel {
        val response = apiService.getCourses(
            page = page, price = price, type = type, difficulty = difficulty
        )
        return response
    }
}

