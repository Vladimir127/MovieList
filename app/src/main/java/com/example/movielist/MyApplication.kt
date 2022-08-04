package com.example.movielist

import android.app.Application
import android.content.Context
import com.example.movielist.di.AppComponent
import com.example.movielist.di.DaggerAppComponent

class MyApplication : Application() {
    val di: AppComponent by lazy {
        DaggerAppComponent.builder()
            .build()
    }
}

val Context.app: MyApplication
    get() = applicationContext as MyApplication