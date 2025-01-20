package com.example.data.network

import com.example.data.models.CourseResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("courses")
    suspend fun getCourses(
        @Query("page") page: Int,
        @Query("display_price") price: String? = null,
        @Query("course_type") type: String? = null,
        @Query("difficulty") difficulty: String? = null,
    ): CourseResponseModel
}
