package com.example.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.taskmanagement.MainActivity
import com.example.taskmanagement.R


const val NOTIFICATION_CHANNEL_ORDERS = 101

class AudioService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action != null && intent.action.equals("action_stop_my_services")) {
            stopSelf()
        }
        sendNotification()
        return START_NOT_STICKY
    }

    private fun sendNotification() {

        val pendingIntent = PendingIntent.getActivity(
            this,
            0, /* Request code */
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE,
        )

        val icon = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(this,"101")


        notificationBuilder.setSmallIcon(R.drawable.ic_bookmark)
        notificationBuilder.setContentTitle("Foreground Service")
        notificationBuilder.setContentText("Foreground Service started")
        notificationBuilder.setLargeIcon(icon)
        notificationBuilder.setAutoCancel(true)
        notificationBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        //if (AppSharedPref.getNotificationSoundEnabled(this)) {
        //     notificationBuilder.setSound(defaultSoundUri)
        //   } else {
        notificationBuilder.setSound(null)
        notificationBuilder.setDefaults(0)
        notificationBuilder.setSilent(true)
        //   }
        notificationBuilder.setContentIntent(pendingIntent)
        notificationBuilder.priority =
                //if (AppSharedPref.getNotificationSoundEnabled(this)) {
                //     NotificationCompat.PRIORITY_HIGH
                //  } else {
            NotificationCompat.PRIORITY_LOW
        //      }
        val notificationManager =
            application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //All notifications should go through NotificationChannel on Android 26 & above

        //All notifications should go through NotificationChannel on Android 26 & above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "101",
                "CHANNEL_NAME",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(NOTIFICATION_CHANNEL_ORDERS, notificationBuilder.build())

        startForeground(NOTIFICATION_CHANNEL_ORDERS, notificationBuilder.build())
    }
}