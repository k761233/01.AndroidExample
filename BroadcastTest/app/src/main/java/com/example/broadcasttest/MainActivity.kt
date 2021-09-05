package com.example.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    companion object {
        var flag: Boolean = false
    }
    private lateinit var stClock: TextView
    private lateinit var btStart: Button

    private fun getComponent() {
        stClock = findViewById(R.id.stClock)
        btStart = findViewById(R.id.btClock)
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            //接收廣播後，解析 Intent 取得字串訊息
            intent.extras?.let {
                var sChannel = "${it.getString("channel")}"
                var sCLock = "${it.getString("clock")}"
                if (sChannel == "MyMessage") {
                    val tv_msg = findViewById<TextView>(R.id.stClock)
                    tv_msg.text = "${it.getString("clock")}"
                }
            }
        }
    }

    private fun register(channel: String) {
        //建立 IntentFilter 物件來指定接收的頻道，並註冊 Receiver
        val intentFilter = IntentFilter(channel)
        registerReceiver(receiver, intentFilter)
        //建立 Intent 物件，使其夾帶頻道資料，並啟動 MyService 服務
        val i = Intent(this, MyService::class.java)
        var bundle: Bundle = Bundle()
        bundle.putString("channel", channel)
        bundle.putBoolean("flag", flag)
        i.putExtras(bundle)
        startService(i)
    }
    //--------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flag = MyService.flag
        getComponent()
        register("MyMessage")
        btStart.text = if (flag) "暫停" else "開始"

        btStart.setOnClickListener {
            flag = !flag
            btStart.text = if (flag) "開始" else "暫停"
            var bundle: Bundle = Bundle()
            bundle.putString("channel", "MyMessage")
            bundle.putBoolean("flag", flag)
            startService(Intent(this, MyService::class.java).putExtras(bundle))
            Toast.makeText(this, if (flag) "計時開始" else "計時暫停", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}