package com.crepuscule.jstu.homework7

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class ImageAdapter (private val imageUrlList: List<String>, private val context: Context) : RecyclerView.Adapter<ImageAdapter.ImageAdapterViewHolder>() {
    inner class ImageAdapterViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val image_entry: ImageView = itemView.findViewById<ImageView>(R.id.image_entry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_entry, parent, false)
        return ImageAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageAdapterViewHolder, position: Int) {
        Glide.with(context)
            .load(imageUrlList[position])
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
            .error(R.drawable.error)
            .into(holder.image_entry)
    }

    override fun getItemCount(): Int {
        return imageUrlList.size
    }
}