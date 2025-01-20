package com.example.itcourses.di

import com.example.core.utils.DateUtils
import org.koin.dsl.module


val coreModule = module {

    single { DateUtils }
}