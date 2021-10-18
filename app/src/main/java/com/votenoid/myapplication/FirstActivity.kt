package com.votenoid.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.votenoid.votenoid.Screens.Launch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

       var handler=Handler()
        handler.postDelayed(Runnable {

            val intent:Intent=Intent(this,Launch::class.java)
                .apply {
                    startActivity(this)
                }
        },3000)
    }


}