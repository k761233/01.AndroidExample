package com.example.gridtest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class List_Skin_Adapter : BaseAdapter() {
    private var mLayInf: LayoutInflater? = null
    var mItemList: List<Map<String, Any>>? = null

    fun Set(context: Context, itemList: ArrayList<Map<String, Any>>?) {
        mLayInf = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mItemList = itemList
    }

    override
    fun getCount(): Int {
        //取得 ListView 列表 Item 的數量
        return mItemList!!.size
    }

    override
    fun getItem(position: Int): Any? {
        //取得 ListView 列表於 position 位置上的 Item
        return position
    }

    override
    fun getItemId(position: Int): Long {
        //取得 ListView 列表於 position 位置上的 Item 的 ID
        return position.toLong()
    }

    override
    fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        //設定與回傳 convertView 作為顯示在這個 position 位置的 Item 的 View。
        //val v = mLayInf!!.inflate(R.layout.activity_main, parent, false)
        //val imgView = v.findViewById<View>(R.id.image) as ImageView
        //val txtView = v.findViewById<View>(R.id.text) as TextView
        //imgView.setImageResource(R.drawable.ic_baseline_ads_click_24)
        //imgView.setImageResource(Integer.valueOf(mItemList.get(position).get("Item icon").toString()));
        //txtView.text = mItemList!![position]["text"].toString()
        //return v
        return null;
    }
}