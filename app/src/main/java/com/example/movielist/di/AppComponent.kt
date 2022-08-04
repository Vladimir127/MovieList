package com.example.movielist.di

import com.example.movielist.ui.list.ListActivity
import dagger.Component

@Component(modules = [DbModule::class, MvvmModule::class])
interface AppComponent {
    fun inject(listActivity: ListActivity)
}