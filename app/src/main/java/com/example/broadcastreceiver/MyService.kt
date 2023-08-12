package com.example.broadcastreceiver

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MyService : Service() {
    lateinit var mplayer:MediaPlayer
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mplayer = MediaPlayer.create(applicationContext, R.raw.bensound_ukulele)
        startMyForegroundService()
    }

//    override fun onStart(intent: Intent?, startId: Int) {
//        super.onStart(intent, startId)
//        mplayer.start()
//    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mplayer.start()
        //startMyForegroundService()
        return START_STICKY
    }

    private fun startMyForegroundService() {
        var NOTIFICATION_ID = 100

        var intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        var pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        var notif = NotificationCompat.Builder(this)
        notif.setContentTitle("Notification")
        notif.setContentText("This notification is for playing service !!")
        notif.setSmallIcon(R.drawable.ic_launcher_background)
        notif.setContentIntent(pendingIntent)

        startForeground(NOTIFICATION_ID, notif.build())

    }

    override fun onDestroy() {
        super.onDestroy()
        mplayer.stop()
    }

}