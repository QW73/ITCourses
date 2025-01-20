package com.example.itcourses.ui.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.CourseEntity
import com.example.domain.repositories.FavoriteCourseRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(val favoriteCourseRepository: FavoriteCourseRepository) : ViewModel() {

    private val _favorites = MutableLiveData<List<CourseEntity>>()
    val favorites: LiveData<List<CourseEntity>> = _favorites

    init {
        fetchFavorites()
    }

    private fun fetchFavorites() {
        viewModelScope.launch {
            try {
                val favoriteCourses = favoriteCourseRepository.getAllFavorites()
                _favorites.postValue(favoriteCourses)
            } catch (e: Exception) {
                Log.e("FavoritesViewModel", "Error fetching favorites: ${e.message}")
            }
        }
    }

    fun removeFromFavorites(courseId: Int) {
        viewModelScope.launch {
            favoriteCourseRepository.removeFromFavorites(courseId)
            _favorites.postValue(_favorites.value?.filterNot { it.id == courseId })
        }
    }

}

