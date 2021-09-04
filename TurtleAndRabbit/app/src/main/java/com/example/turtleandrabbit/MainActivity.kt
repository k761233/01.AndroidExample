package com.example.turtleandrabbit

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.loader.content.AsyncTaskLoader

class MainActivity : AppCompatActivity() {
    private var miRabbitProgress = 0
    private var miTurtleProgress = 0
    lateinit var btStart: Button
    lateinit var sbRabbit:SeekBar
    lateinit var sbTurtle:SeekBar

    private fun getComponent() {
        btStart = findViewById(R.id.btStart)
        sbRabbit = findViewById(R.id.sbRabbit)
        sbTurtle = findViewById(R.id.sbTurtle)
    }
    private val mHandler= Handler(Handler.Callback { msg ->
        when (msg.what) {
            1 -> sbRabbit.progress = miRabbitProgress
        }
        if (miRabbitProgress >= 100 && miTurtleProgress < 100) {
            Toast.makeText(this, "兔子勝利", Toast.LENGTH_SHORT).show()
            btStart.isEnabled = true
        }
        true
    })
    private fun runAsyncTask() {
        object: AsyncTask<Void, Int, Boolean>() {
            override fun doInBackground(vararg p0: Void?): Boolean {
                TODO("Not yet implemented")

                while (miRabbitProgress < 100 && miTurtleProgress <= 100) {
                    try {
                        Thread.sleep(100)
                    }
                    catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    miTurtleProgress += (Math.random() * 3).toInt()
                    publishProgress(miTurtleProgress)
                }
            }

            override fun onProgressUpdate(vararg values: Int?) {
                super.onProgressUpdate(*values)
                values[0]?.let {
                    sbTurtle.progress = it
                }
            }

            override fun onPostExecute(result: Boolean?) {
                super.onPostExecute(result)
                Toast.makeText(this@MainActivity, "烏龜勝利", Toast.LENGTH_SHORT).show()
                btStart.isEnabled = true
            }
        }.execute()
    }
    private fun runThread() {
        object : Thread() {
            override fun run(){
                while (miRabbitProgress <= 100 && miTurtleProgress < 100) {
                    try {
                        Thread.sleep(100)
                    }
                    catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    miRabbitProgress += (Math.random() * 3).toInt()
                    val msg = Message()
                    msg.what = 1
                    mHandler.sendMessage(msg)
                }
            }
        }.start()
    }
    // ========================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btStart.setOnClickListener {
            btStart.isEnabled = false
            miRabbitProgress = 0
            miTurtleProgress = 0
            sbRabbit.progress = miRabbitProgress
            sbTurtle.progress = miTurtleProgress

            runThread()
            runAsyncTask()
        }
    }
}