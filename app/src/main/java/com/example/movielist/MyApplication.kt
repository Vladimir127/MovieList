package com.example.movielist

import android.app.Application
import android.content.Context

class MyApplication : Application()

// Чтобы к app можно было обращаться из Activity без инициализации
val Context.app: MyApplication
    get() = applicationContext as MyApplication