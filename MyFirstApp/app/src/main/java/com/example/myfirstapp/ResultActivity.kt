package com.example.myfirstapp

import android.graphics.Color
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class ResultActivity : AppCompatActivity() {

    lateinit var stResult:TextView;

    fun onSNACK(view: View, sMsg:String) {
        val snackbar = Snackbar.make(view, sMsg,
            Snackbar.LENGTH_LONG).setAction("Action", null)

        snackbar.setActionTextColor(Color.BLUE)

        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.LTGRAY)
        val textView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.BLUE)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textView.textSize = 28f

        snackbar.show()
    }

    private fun findViews() {
        stResult = findViewById(R.id.stResult);
    }
    /* -------------------------------------------------------------------------------------- */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        findViews()
        val message = intent.getStringExtra("Result");
        stResult.text = message;
        //Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();
    }

    fun btReturn_Click(view: View) {
        onSNACK(view, stResult.text.toString());
        //val returnBtn = Intent(this, MainActivity::class.java);
        //startActivity(returnBtn)
    }

    override fun onRestart() {
        super.onRestart()
        //Toast.makeText(this, "onRestart", Toast.LENGTH_LONG).show();
    }

    override fun onStart() {
        super.onStart()
        //Toast.makeText(this, "onStart", Toast.LENGTH_LONG).show();
    }

    override fun onResume() {
        super.onResume()
        //Toast.makeText(this, "onResume", Toast.LENGTH_LONG).show();
    }

    override fun onPause() {
        super.onPause()
        //Toast.makeText(this, "onPause", Toast.LENGTH_LONG).show();
    }

    override fun onStop() {
        super.onStop()
        //Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
    }

    override fun onDestroy() {
        super.onDestroy()
        //Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
    }
}