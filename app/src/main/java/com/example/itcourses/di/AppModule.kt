package com.example.itcourses.di

import com.example.itcourses.ui.account.AccountViewModel
import com.example.itcourses.ui.course_information.CourseInformationViewModel
import com.example.itcourses.ui.favorites.FavoritesViewModel
import com.example.itcourses.ui.home.HomeViewModel
import com.example.itcourses.ui.navigation.CustomBottomNavManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel {  HomeViewModel(get(), get()) }
    viewModel { FavoritesViewModel(get()) }
    viewModel { AccountViewModel() }
    viewModel { CourseInformationViewModel() }

    single { CustomBottomNavManager(get()) }

}