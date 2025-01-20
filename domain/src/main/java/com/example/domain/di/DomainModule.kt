package com.example.itcourses.di

import com.example.domain.repositories.CourseRepository
import com.example.domain.repositories.CourseRepositoryImpl
import com.example.domain.repositories.FavoriteCourseRepository
import com.example.domain.repositories.FavoriteCourseRepositoryImpl
import org.koin.dsl.module


val domainModule = module {

    single<CourseRepository> { CourseRepositoryImpl(get()) }

    single<FavoriteCourseRepository> { FavoriteCourseRepositoryImpl(get()) }

}
