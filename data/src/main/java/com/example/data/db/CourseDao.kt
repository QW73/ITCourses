package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CourseDao {
    @Insert
    suspend fun insert(course: CourseEntity)

    @Query("DELETE FROM favorite_courses WHERE id = :courseId")
    suspend fun delete(courseId: Int)

    @Query("SELECT * FROM favorite_courses")
    suspend fun getAll(): List<CourseEntity>

    @Query("SELECT COUNT(*) FROM favorite_courses WHERE id = :courseId")
    suspend fun isCourseFavorite(courseId: Int): Int
}