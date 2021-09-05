package com.example.servicetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var btStart : Button
    private fun showToast(msg:String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    private fun getComponent() {
        btStart = findViewById(R.id.btStart)
    }
    // --------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getComponent()
        btStart.setOnClickListener() {
            startService(Intent(this, MyService::class.java))
            showToast("啟動 Service")
            finish()
        }
    }

}