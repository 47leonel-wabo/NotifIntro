package com.aiwadev.notifintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aiwadev.notifintro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Using data binding to inflate our layout
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
    }
}