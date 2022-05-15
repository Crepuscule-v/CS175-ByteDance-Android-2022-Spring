package com.crepuscule.jstu.homework3

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView


class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {
    private lateinit var coin: ImageView
    private lateinit var like: ImageView
    private lateinit var store: ImageView
    private lateinit var redCoin: ImageView
    private lateinit var redLike: ImageView
    private lateinit var redStore: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViewIcon()
        bindClickListener()
    }

    // 与 view 中元素绑定
    private fun bindViewIcon() {
        coin = findViewById(R.id.coin)
        like = findViewById(R.id.like)
        store = findViewById(R.id.store)
        redCoin = findViewById(R.id.coin_red)
        redLike = findViewById(R.id.like_red)
        redStore = findViewById(R.id.store_red)
    }

    // 绑定点击事件
    private fun bindClickListener() {
        // 短按事件
        coin.setOnClickListener(this)
        like.setOnClickListener(this)
        store.setOnClickListener(this)

        coin.setOnLongClickListener(this)
        like.setOnLongClickListener(this)
        store.setOnLongClickListener(this)

        // 长按事件
        redCoin.setOnClickListener(this)
        redLike.setOnClickListener(this)
        redStore.setOnClickListener(this)

        redCoin.setOnLongClickListener(this)
        redLike.setOnLongClickListener(this)
        redStore.setOnLongClickListener(this)
    }

    // 每个图标都包括红色和无色两种，点击时切换
    // 点击时同时上移 + 旋转
    private fun viewClickAnimation(selfIcon: View?, anotherIcon: View?) {
        val posAnimator = ValueAnimator.ofFloat(0f, -50f, 0f)  // 0 -> -50 -> 0
        posAnimator.addUpdateListener {
            val curValue:Float = it.animatedValue as Float      // 强制类型转化
            selfIcon?.translationY = curValue
        }
        val rotAnimator = ValueAnimator.ofFloat(0f, -50f, 0f)
        rotAnimator.addUpdateListener {
            val curValue:Float = it.animatedValue as Float
            selfIcon?.rotation = curValue
        }
        // 绑定两个动画
        val animatorSet = AnimatorSet()
        animatorSet.play(posAnimator).with(rotAnimator)
        animatorSet.duration = 500  // 0.5s
        animatorSet.addListener(object: Animator.AnimatorListener {
            override fun onAnimationStart(value: Animator?) {}
            // 动画结束时切换图标
            override fun onAnimationEnd(value: Animator?) {
                selfIcon?.visibility = View.INVISIBLE
                anotherIcon?.visibility = View.VISIBLE
            }
            override fun onAnimationCancel(value: Animator?) {}
            override fun onAnimationRepeat(value: Animator?) {}
        })
        animatorSet.start()
    }

    override fun onClick(icon: View?) {
        // 为每一个按键设置点击事件
        when (icon) {
            coin -> { viewClickAnimation(coin, redCoin) }
            like -> { viewClickAnimation(like, redLike) }
            store -> { viewClickAnimation(store, redStore) }
            redCoin -> { viewClickAnimation(redCoin, coin) }
            redLike -> { viewClickAnimation(redLike, like) }
            redStore -> { viewClickAnimation(redStore, store) }
        }
    }

    override fun onLongClick(icon: View?): Boolean {
        when (icon) {
            coin,like,store -> {
                coin.visibility = View.VISIBLE
                like.visibility = View.VISIBLE
                store.visibility = View.VISIBLE
                redCoin.visibility = View.INVISIBLE
                redLike.visibility = View.INVISIBLE
                redStore.visibility = View.INVISIBLE
                viewClickAnimation(coin, redCoin)
                viewClickAnimation(like, redLike)
                viewClickAnimation(store, redStore)
            }
            redCoin,redLike,redStore -> {
                coin.visibility = View.INVISIBLE
                like.visibility = View.INVISIBLE
                store.visibility = View.INVISIBLE
                redCoin.visibility = View.VISIBLE
                redLike.visibility = View.VISIBLE
                redStore.visibility = View.VISIBLE
                viewClickAnimation(redCoin, coin)
                viewClickAnimation(redLike, like)
                viewClickAnimation(redStore, store)
            }
        }
        return true
    }
}
