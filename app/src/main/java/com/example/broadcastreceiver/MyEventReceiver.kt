package com.example.broadcastreceiver

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class MyEventReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent!!.action==Intent.ACTION_SCREEN_OFF){
            context!!.startService(Intent(context, MyService::class.java))
        }else if (intent.action==Intent.ACTION_AIRPLANE_MODE_CHANGED){
            //notification example for version code above oreo
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
                var notifyId = 0
                var channelId = "channel_id_0"
                var name = context!!.resources.getString(R.string.app_name)
                var importance = NotificationManager.IMPORTANCE_HIGH

                var notifChannel = NotificationChannel(channelId, name, importance)

                var builder = Notification.Builder(context)
                builder.setSmallIcon(R.drawable.ic_launcher_background)
                builder.setContentTitle("Notification")
                builder.setContentText("This notification is created by Nisha !!")
                builder.setChannelId(channelId)

                /***
                pending intent used to send intent to another application i.e notification manager , alarmmanager etc.
                if we send intent directly then it will execute without our app permissions
                pendingintent wraps intent to be executed by another applications with our apps permissions
                ***/
                var intent = Intent(context, MainActivity::class.java)
                var pIntent = PendingIntent.getActivities(context, 0, arrayOf(intent), PendingIntent.FLAG_UPDATE_CURRENT)

                builder.setContentIntent(pIntent)

                var mNotificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
                    mNotificationManager.createNotificationChannel(notifChannel)
                }
                mNotificationManager.notify(notifyId, builder.build())

            }
        }

    }

}