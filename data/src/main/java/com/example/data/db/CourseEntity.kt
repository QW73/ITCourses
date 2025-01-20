package com.example.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_courses")
data class CourseEntity(
    @PrimaryKey val id: Int,
    val title: String?,
    val price: String?,
    val type: String?,
    val difficulty: String?,
    val cover: String?,
    val date: String?,
    val learners: Int,
    val summary: String?,
    val description: String?,
    val authors: String?
)