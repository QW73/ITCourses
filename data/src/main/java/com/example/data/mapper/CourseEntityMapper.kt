package com.example.data.mapper

import com.example.data.db.CourseEntity
import com.example.data.models.CourseModel

fun CourseEntity.toCourseModel(): CourseModel {
    return CourseModel(id = this.id,
        title = this.title,
        price = this.price,
        type = this.type,
        difficulty = this.difficulty,
        cover = this.cover,
        date = this.date,
        learners = this.learners,
        summary = this.summary,
        favorite = true,
        description = this.description,
        authors = this.authors?.split(",")?.map { it.trim().toIntOrNull() })
}