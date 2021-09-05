package com.example.servicetest

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {

    private fun showActivity(packageContext: Context, cxCUr: Class<*>?)
    {
        val intent = Intent(packageContext, cxCUr)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        packageContext.startActivity(intent)
    }
    override fun onCreate() {
        super.onCreate()
        Thread (
            Runnable {
                try {
                    Thread.sleep(5000)
                    showActivity(this@MyService, MainActivity2::class.java)
                } catch (e:InterruptedException) {
                    e.printStackTrace()
                }
            }
        ).start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        return Service.START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}