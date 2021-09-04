package com.example.sqlitetest

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var dbrw: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // insert
        dbrw = MyDBHelper(this, "mytable").writableDatabase
        val cv = ContentValues()
        cv.put("book", "百科全書")
        cv.put("price", 900)
        dbrw.insert("mytable", null, cv)
        dbrw.close()

        /*
        // query
        var number = ""
        var book = ""
        var price = ""
        val colum = arrayOf("book", "price")
        val c = dbrw.query("mytable", colum,
            "book='百科全書'",
            null, null, null, null)
        if (c.count > 0)
        {
            c.moveToFirst()
            for(i in 0 until c.count)
            {
                number += "$i\n"
                book += "${c.getString(0)}"
                price += "${c.getString(1)}"
                c.moveToNext()
            }
        }
        c.close()

        // update
        val cvupd = ContentValues()
        cvupd.put("price", 200)
        dbrw.update("mytable", cvupd, "book='百科全書'",
            null)

        // delete
        dbrw.delete("mytable", "book='百科全書'", null)

        // query all
        val c_all = dbrw.rawQuery("SELECT * FROM mytable", null)
        Toast.makeText(this, "共有${c_all.count}筆資料", Toast.LENGTH_SHORT).show()
        c_all.close()

        try {
            dbrw.execSQL(
                "INSERT INTO mytable(book, price) VALUES (?,?)", arrayOf("百科全書", 900)
            )
            dbrw.execSQL(
                "UPDATE mytable SET price = 200 WHERE book LIKE '百科全書'"
            )
            dbrw.execSQL(
                "DELETE FROM mytable WHERE book LIKE '百科全書'"
            )
        } catch (e:Exception) {

        }
        */
    }
}