package com.example.myfirstapp

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var ebHigh:EditText;
    lateinit var ebWeight:EditText;
    lateinit var stResult:TextView;
    lateinit var fab:FloatingActionButton;
    var msUserId:String = "";
    var miCount:Int = 0;

    private fun findViews() {
        ebWeight = findViewById(R.id.ebWeight);
        ebHigh = findViewById(R.id.ebHigh);
        ebWeight = findViewById(R.id.ebWeight);
        stResult = findViewById(R.id.stResult);
        fab = findViewById(R.id.fab);
    }
    private fun File_Write(sFile:String, sKey:String, sVal:String, iPriority:Int = MODE_PRIVATE)
    {
        val fAccess :SharedPreferences = getSharedPreferences(sFile, iPriority)
        fAccess.edit()
            .putString(sKey, sVal)
            .commit()
    }
    private fun File_Read(sFile:String, sKey:String, iPriority:Int = MODE_PRIVATE):String
    {
        val sRet:String;
        val fAccess :SharedPreferences = getSharedPreferences(sFile, iPriority)
        sRet = fAccess.getString(sKey, "").toString()
        return sRet;
    }
    /* -------------------------------------------------------------------------------------- */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews();

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }
        File_Write("Login", "UserID", "jack")
        msUserId = File_Read("Login", "UserID")
        //toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings) {
            miCount++
            stResult.setText(miCount.toString())
        }
        return true
    }

    fun btCalc_OnClick(view: View) {
        val dHigh   = ebHigh.text.toString().toFloat() / 100.0;
        val dWeight = ebWeight.text.toString().toFloat();
        val builder = AlertDialog.Builder(this);
        val intent: Intent;
        intent = Intent(this, ResultActivity::class.java);

        stResult.text = String.format("計算結果 : %.2f", dWeight / Math.pow(dHigh, 2.0));
        intent.putExtra("Result", stResult.text.toString());
        startActivity(intent);
        /*
        builder.setMessage(stResult.text);
        builder.setTitle(resources.getString(R.string.calculator_bmi));
        builder.setPositiveButton("Ok", null);
        builder.setNeutralButton("Cancel", null);
        builder.show();
        */
    }
}