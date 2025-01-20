package com.example.domain.repositories

import com.example.data.db.CourseEntity


interface FavoriteCourseRepository {
    suspend fun addToFavorites(course: CourseEntity)
    suspend fun removeFromFavorites(courseId: Int)
    suspend fun getAllFavorites(): List<CourseEntity>
    suspend fun isCourseFavorite(courseId: Int): Boolean
}