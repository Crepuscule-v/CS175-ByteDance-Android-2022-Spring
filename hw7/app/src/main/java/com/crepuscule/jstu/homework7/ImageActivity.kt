package com.crepuscule.jstu.homework7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class ImageActivity : AppCompatActivity() {
    private lateinit var imageUrlList : List<String>
    private lateinit var imageViewAdapter : ImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        setImageUrlList()
        imageViewAdapter = ImageAdapter(imageUrlList, this)
        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = imageViewAdapter
    }

    private fun setImageUrlList() {
        imageUrlList = listOf(
            "https://t7.baidu.com/it/u=4162611394,4275913936&fm=193&f=GIF",
            "https://t7.baidu.com/it/u=2851687453,2321283050&fm=193&f=GIF",
            "https://t7.baidu.com/it/u=4069854689,43753836&fm=193&f=GIF",
            "https://t7.baidu.com/it/u=1653814446,2847580380&fm=193&f=GIF",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20181224%2F739a82a2bc16495889ab619b52132798.gif&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1655816289&t=9af87c484c1889349541d021d6d8590a",
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage4.vipc.cn%2Farticle%2F5e698c8adf84a40011c2d7be.gif&refer=http%3A%2F%2Fimage4.vipc.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1655816308&t=54b3e58fd50e6f6d7659baa6b427f2d7"
        )
    }
}