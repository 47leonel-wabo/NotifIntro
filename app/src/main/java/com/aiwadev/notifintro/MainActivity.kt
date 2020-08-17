package com.aiwadev.notifintro

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.aiwadev.notifintro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    private val CHANNEL_ID = "notif_channel_id"
    private val CHANNEL_NAME = "notif_channel_name"
    private val NOTIF_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Using data binding to inflate our layout
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        createNotification()

        //Creating a pending intent
        val notifIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(notifIntent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("My First Notification")
                .setContentText("This is my awesome notification, i know it is a bit...")
                .setSmallIcon(R.drawable.ic_function)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .build()

        val notifManager = NotificationManagerCompat.from(this)

        bind.notifButton.setOnClickListener {
            notifManager.notify(NOTIF_ID, notification)
        }
    }

    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
                    .apply {
                        lightColor = Color.GREEN
                        enableLights(true)
                    }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}