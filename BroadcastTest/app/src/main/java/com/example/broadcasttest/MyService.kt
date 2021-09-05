package com.example.broadcasttest

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder

class MyService : Service() {

    companion object {
        var flag: Boolean = false
        var channel: String = ""
    }
    private var h = 0
    private var m = 0
    private var s = 0

    //發送廣播
    private fun broadcast(bundle: Bundle) =
        sendBroadcast(Intent(channel).putExtras(bundle))
    // ===============================================================================
    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //解析 Intent 取得字串訊息
        intent?.extras?.let {
            channel = it.getString("channel", "")
            flag = it.getBoolean("flag", false)
        }

        object: Thread() {
            override fun run() {
                while (flag) {
                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                    s++
                    if (s >= 60) {
                        s = 0
                        m++
                        if (m >= 60) {
                            m = 0
                            h++
                        }
                    }
                    val sClock = "%02d:%02d:%02d".format(h, m, s)
                    var bundle: Bundle = Bundle()
                    bundle.putString("channel", "MyMessage")
                    bundle.putString("clock", sClock)
                    broadcast(bundle)
                }
            }
        }.start()//啟動執行緒

        return Service.START_STICKY
    }
}