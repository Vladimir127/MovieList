package com.example.movielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Добавляет запуск StartActivity в очередь по истечении 3 секунд
        handler.postDelayed({
            startActivity(Intent(this@SplashActivity, ListActivity::class.java))
            finish()
        }, 1000)
    }

    override fun onDestroy() {
        // Снимаем задачу с Handler, если пользователь решил выйти из приложения
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}