package com.estholon.realtimedatabasechat.ui.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.estholon.realtimedatabasechat.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}