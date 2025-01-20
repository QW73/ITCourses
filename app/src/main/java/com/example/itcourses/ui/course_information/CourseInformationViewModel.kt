package com.example.itcourses.ui.course_information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.CourseEntity
import com.example.data.models.CourseModel
import com.example.domain.repositories.FavoriteCourseRepository
import kotlinx.coroutines.launch

class CourseInformationViewModel(private val favoriteCourseRepository: FavoriteCourseRepository) :
    ViewModel() {

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun checkIfFavorite(courseId: Int) {
        viewModelScope.launch {
            val favorite = favoriteCourseRepository.isCourseFavorite(courseId)
            _isFavorite.postValue(favorite)
        }
    }

    fun toggleFavorite(course: CourseModel) {
        viewModelScope.launch {
            val isCurrentlyFavorite = _isFavorite.value ?: false
            if (isCurrentlyFavorite) {
                favoriteCourseRepository.removeFromFavorites(course.id)
            } else {
                val courseEntity = CourseEntity(
                    id = course.id,
                    title = course.title,
                    price = course.price,
                    type = course.type,
                    difficulty = course.difficulty,
                    cover = course.cover,
                    date = course.date,
                    learners = course.learners,
                    summary = course.summary,
                    description = course.description,
                    authors = course.authors.toString()
                )
                favoriteCourseRepository.addToFavorites(courseEntity)
            }
            _isFavorite.postValue(!isCurrentlyFavorite)
        }
    }
}

