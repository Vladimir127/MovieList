package com.example.movielist

import android.app.Application
import android.content.Context
import com.example.movielist.di.DaggerMyComponent
import com.example.movielist.di.MyComponent
import com.example.movielist.di.MyModule

class MyApplication : Application() {
    val di: MyComponent by lazy {
        DaggerMyComponent.builder()
            .myModule(MyModule())
            .build()
    }
}

val Context.app: MyApplication
    get() = applicationContext as MyApplication