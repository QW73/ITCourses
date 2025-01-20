package com.example.itcourses.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.CourseEntity
import com.example.data.models.CourseModel
import com.example.data.models.MetaModel
import com.example.domain.repositories.CourseRepository
import com.example.domain.repositories.FavoriteCourseRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val courseRepository: CourseRepository,
    val favoriteCourseRepository: FavoriteCourseRepository
) : ViewModel() {

    private val _courses = MutableLiveData<List<CourseModel>>()
    val courses: LiveData<List<CourseModel>> = _courses

    private val _paginationInfo = MutableLiveData<MetaModel>()
    val paginationInfo: LiveData<MetaModel> = _paginationInfo


    fun fetchCourses(
        page: Int,
        pagesToLoad: Int = 1,
        price: String? = null,
        type: String? = null,
        difficulty: String? = null
    ) {
        viewModelScope.launch {
            try {
                var loadedPages = 0
                var currentPage = page

                while (loadedPages < pagesToLoad) {
                    val response =
                        courseRepository.getCoursesOnPage(currentPage, price, type, difficulty)

                    _paginationInfo.postValue(response.meta)

                    if (response.courses.isNotEmpty()) {
                        val currentCourses = _courses.value.orEmpty()
                        val newCourses = (currentCourses + response.courses).distinctBy { it.id }
                        _courses.postValue(newCourses)
                        loadedPages++
                    }

                    if (!response.meta.hasNext) {
                        break
                    }

                    currentPage++
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching courses: ${e.message}")
            }
        }
    }

    fun toggleFavorite(course: CourseModel) {
        viewModelScope.launch {
            val isFavorite = favoriteCourseRepository.isCourseFavorite(course.id)

            if (isFavorite) {
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

            _courses.postValue(_courses.value?.map {
                if (it.id == course.id) it.copy(favorite = !isFavorite) else it
            })
        }
    }


}
