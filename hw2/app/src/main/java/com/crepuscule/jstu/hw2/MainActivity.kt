package com.crepuscule.jstu.hw2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var level = listOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv = findViewById<RecyclerView>(R.id.recycler_view)
        rv.layoutManager = LinearLayoutManager(this)
        val adapter = SearchItemAdapter(this)
        val elem = listOf<String>("两数之和","两数相加","无重复字符的最长子串","寻找两个正序数组的中位数",
            "最长回文子串","Z 字形变换","整数反转","回文数")
        this.level = listOf<String>("简单","中等","中等","困难","中等","中等","中等",
            "简单")

        adapter.setContentList(elem)
        rv.adapter = adapter
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val et = findViewById<EditText>(R.id.words_et)      // 编辑框
        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                adapter.setFilter(p0.toString())
            }
        })
    }

    // 获取每道题的详细介绍
    fun getDetails(): List<String>? {
        var str: String? = ""
        val detailsFile = application.assets.open("details.txt")
        val len: Int = detailsFile.available()       // 获取行数
        val buffer = ByteArray(len)
        detailsFile.read(buffer)
        str = String(buffer)
        detailsFile.close()
        return str?.split("\n")
    }
}


