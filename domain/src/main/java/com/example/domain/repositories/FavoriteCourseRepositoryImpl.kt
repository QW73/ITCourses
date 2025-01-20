package com.example.domain.repositories

import com.example.data.db.CourseDao
import com.example.data.db.CourseEntity

class FavoriteCourseRepositoryImpl(private val courseDao: CourseDao) : FavoriteCourseRepository {
    override suspend fun addToFavorites(course: CourseEntity) {
        courseDao.insert(course)
    }

    override suspend fun removeFromFavorites(courseId: Int) {
        courseDao.delete(courseId)
    }

    override suspend fun getAllFavorites(): List<CourseEntity> {
        return courseDao.getAll()
    }

    override suspend fun isCourseFavorite(courseId: Int): Boolean {
        return courseDao.isCourseFavorite(courseId) > 0
    }
}