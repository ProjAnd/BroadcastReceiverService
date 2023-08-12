package com.example.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    lateinit var infilter :IntentFilter
    lateinit var mReceiver : MyEventReceiver
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //for some events register broadcast receiver in activity
        //for some events registering broadcast event only in manifest will be sufficient
//        infilter = IntentFilter()
//        //infilter.addAction(Intent.ACTION_SCREEN_OFF)
//        infilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
//        mReceiver = MyEventReceiver()
//        registerReceiver(mReceiver, infilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        //unregisterReceiver(mReceiver)
    }

}