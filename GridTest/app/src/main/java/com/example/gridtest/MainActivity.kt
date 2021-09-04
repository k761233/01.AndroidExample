package com.example.gridtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {

    private val ITEM_TITLE = "Item title"
    private val ITEM_ICON = "Item icon"
    lateinit var dgvTable : GridView
    lateinit var lvTable : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sArrItemText = arrayOf("餘額查詢", "交易明細", "最新消息", "投資理財", "離開")

        /*
        dgvTable = findViewById(R.id.dgvCur)
        val adapter4Dgv = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, sArrItemText)
        dgvTable.adapter = adapter4Dgv;
        */

        lvTable = findViewById(R.id.lvCur)
        val objArrItemImage = intArrayOf(R.drawable.ic_baseline_ads_click_24,
            R.drawable.ic_baseline_align_horizontal_center_24,
            R.drawable.ic_baseline_anchor_24,
            R.drawable.ic_baseline_anchor_24,
            R.drawable.ic_baseline_anchor_24)
        val items = ArrayList<Map<String, Any>>()
        for (i in sArrItemText.indices) {
            val item = HashMap<String, Any>()
            item["image"] = objArrItemImage[i]
            item["text"] = sArrItemText[i]
            items.add(item)
        }
        val adapter = SimpleAdapter(this, items, R.layout.list_skin, arrayOf("image", "text"),  intArrayOf(R.id.image, R.id.text))
        lvTable.adapter = adapter
        /*
        //定義 ListView 每個 Item 的資料
        val itemList: ArrayList<Map<String, Any>> = ArrayList()
        val regionIconList = resources.obtainTypedArray(R.array.region_icon_list)
        for (i in 0 until sArrItemText.count()) {
            val item: MutableMap<String, Any> = HashMap()
            item["image"] = regionIconList.getResourceId(i, 0)
            item["text"] = sArrItemText.get(i)
            itemList.add(item)
        }
        val adapter4Lv = List_Skin_Adapter()
        adapter4Lv.Set(this, itemList)
        //val adapter4Lv = ArrayAdapter(this, R.layout.list_skin, itemList)
        lvTable.adapter = adapter4Lv
        */
    }

}
