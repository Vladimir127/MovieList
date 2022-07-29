package com.example.movielist.di

import com.example.movielist.ui.list.ListActivity
import dagger.Component

@Component(modules = [MyModule::class])
interface MyComponent {
    fun inject(listActivity: ListActivity)
}