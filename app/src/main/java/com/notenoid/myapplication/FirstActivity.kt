package com.notenoid.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.notenoid.notenoid.Screens.Launch

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val intent: Intent = Intent(this, Launch::class.java)
            .apply {
                startActivity(this)
            }
    }


}