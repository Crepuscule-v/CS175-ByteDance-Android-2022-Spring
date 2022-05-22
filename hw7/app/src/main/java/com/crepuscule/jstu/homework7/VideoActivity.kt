package com.crepuscule.jstu.homework7

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController

class VideoActivity : AppCompatActivity() {
    private lateinit var myVideoView : MyVideoLayout
    private lateinit var start_btn : Button
    private lateinit var stop_btn : Button
    private lateinit var replay_btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        init()
    }

    private fun init() {
        myVideoView = findViewById(R.id.my_video)
        start_btn = findViewById(R.id.start_btn)
        stop_btn = findViewById(R.id.stop_btn)
        replay_btn = findViewById(R.id.replay_btn)

        start_btn.setOnClickListener {myVideoView.start()}
        stop_btn.setOnClickListener{ myVideoView.pause()}
        replay_btn.setOnClickListener{myVideoView.resume()}

        // 设置资源路径
        myVideoView.setVideoPath(getVideoPath(R.raw.my_video))
        myVideoView.setMediaController(MediaController(this))
    }

    private fun getVideoPath(resId: Int): String {
        return "android.resource://" + this.packageName + "/" + resId
    }

    // 横屏，竖屏
    @Override
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setContentView(R.layout.activity_video)
        init()
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportActionBar?.hide()
        } else {
            supportActionBar?.show()
        }
    }
}