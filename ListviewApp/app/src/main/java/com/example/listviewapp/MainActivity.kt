package com.example.listviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var lvObj:ListView
    lateinit var spObj:Spinner
    lateinit var ebObj:EditText
    lateinit var btAdd:Button
    lateinit var btClear:Button

    private fun showAlert(myAdapter:ArrayAdapter<String>, sOpt:String, sMsg:String, objItem:String?)
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Androidly Alert")
        builder.setMessage(sMsg)
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.yes, Toast.LENGTH_SHORT).show()
            if (sOpt == "clear")
                myAdapter.clear()
            if (sOpt == "remove")
                myAdapter.remove(objItem)
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton("Maybe") { dialog, which ->
            Toast.makeText(applicationContext,
                "Maybe", Toast.LENGTH_SHORT).show()
        }

        builder.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)
        myAdapter.addAll("三個鉛筆", "四個腳踏車")
        lvObj = findViewById(R.id.main_listview)
        ebObj = findViewById(R.id.msg_input)
        btAdd = findViewById(R.id.btn_add)
        btClear = findViewById(R.id.btn_clear)
        spObj = findViewById(R.id.spinner)

        // Button click listener
        btClear.setOnClickListener {
            // Show the yes, no and cancel buttons on an alert dialog
            showAlert(myAdapter, "clear", "確定要刪除全部item嗎？", "")
        }

        btAdd.setOnClickListener {
            val msg = ebObj.text.toString()
            if (msg != "")// 如果'輸入字串'不為空字串，將'輸入字串'插入到myAdapter內
                myAdapter.insert(msg, 0)
        }

        // 當main_listview中某個item被點擊時
        lvObj.setOnItemClickListener { adapterView, view, i, l ->
            val msg = myAdapter.getItem(i)

            showAlert(myAdapter, "remove", "確定要刪除$msg？", msg)
        }
        spObj.adapter = myAdapter
        lvObj.adapter = myAdapter
    }

}