package com.crepuscule.jstu.hw2

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Color


class SearchItemAdapter(activity:MainActivity) : RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder>() {
    private val contentList = mutableListOf<String>()
    private val filteredList = mutableListOf<String>()
    private val mainActivity = activity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val v = View.inflate(parent.context, R.layout.activity_search_item_layout, null)
        val viewholder = SearchItemViewHolder(v)
        viewholder.itemView.setOnClickListener {
            val position = viewholder.adapterPosition
            val details = mainActivity.getDetails()         // 获取题目详情
            val detailElem = details?.get(position)         // 对应位置的 detail
            val intent = Intent(mainActivity, Details::class.java)
            intent.putExtra("detailElem", detailElem)
            mainActivity.startActivity(intent)
        }
        return viewholder
    }

    override fun onBindViewHolder(viewholder: SearchItemViewHolder, position: Int) {
        viewholder.bind(position, filteredList[position], mainActivity.level[position])
    }

    override fun getItemCount(): Int = filteredList.size

    fun setContentList(list: List<String>) {
        contentList.clear()
        contentList.addAll(list)
        filteredList.clear()
        filteredList.addAll(list)
        notifyDataSetChanged()
    }

    fun setFilter(keyword: String?) {
        filteredList.clear()
        if (keyword?.isNotEmpty() == true) {
            filteredList.addAll(contentList.filter {it.contains(keyword)})
        } else {
            filteredList.addAll(contentList)
        }
        notifyDataSetChanged()
    }

    class SearchItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tv = view.findViewById<TextView>(R.id.search_item_tv)
        private val level = view.findViewById<TextView>(R.id.level)
        private val colorMap = mapOf<String, String>("简单" to "#33CC33", "中等" to "#9999FF", "困难" to "#FF3333")

        fun bind(position: Int, content: String, probLevel: String) {
            val pos = "${(position+1).toString()}.$content"
            tv.text = pos
            level.text = probLevel
            level.setTextColor(Color.parseColor(colorMap[probLevel]))
        }
    }

}
