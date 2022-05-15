package com.crepuscule.jstu.homework4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

class ClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()

    /*
    * 可以从UI线程调用invalidate()方法
    * 也可以从非UI线程调用postInvalidate()
    * 以便告诉android在对其进行一些更改后更新我们的自定义视图
    */
    /*
    * 我们可以在子线程中将runnable加入到主线程的MessageQueue
    * 然后主线程将调用runnable的方法
    * 可以在此方法中更新主线程UI。
    * */

    init {
        val handler = Handler(Looper.getMainLooper())
        val runObj = object: Runnable {
            override fun run() {
                postInvalidate()
                handler.postDelayed(this, 1000)     // 每一秒向 UI 线程发送一次 runnable 对象来更新 UI
            }
        }
        handler.post(runObj)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // 获取当前时间
        val calender = Calendar.getInstance()
        val hour = calender.get(Calendar.HOUR)
        val minute = calender.get(Calendar.MINUTE)
        val second = calender.get(Calendar.SECOND)

        // 计算三个指针各自对应的角度
        Log.d("hour", hour.toString())
        val hourAngle : Double = 2 * Math.PI * (hour % 12 + minute / 60.0f + second / 3600.0f) / 12.0f

        Log.d("hour angle", (360.0f * hourAngle / (2 * Math.PI)).toString())
        val minAngle : Double = 2 * Math.PI * (minute + second / 60.0f) / 60.0f
        val secAngle : Double = 2 * Math.PI * (second / 60.0f)

        // 中心坐标
        var cx = measuredWidth.toFloat() / 2
        var cy = measuredHeight.toFloat() / 2

        // 绘制刻度
        paint.color = Color.WHITE
        paint.strokeWidth = 10f
        // 时刻度
        for (i in 1..12) {
            canvas?.drawLine(cx, 0f, cx, 0.1f * cy, paint)
            canvas?.rotate(30f, cx, cy)
        }
        // 分刻度
        paint.color = Color.GRAY
        paint.alpha = 70
        for (i in 1..60) {
            canvas?.drawLine(cx, 0f, cx, 0.07f * cy, paint)
            canvas?.rotate(6f, cx, cy)
        }

        // 指针
        paint.alpha = 100
        paint.color = Color.BLACK
        paint.strokeWidth = 20f
        canvas?.drawLine(cx.toFloat(), cy.toFloat(),
            (cx + 0.6 * cx * sin(hourAngle)).toFloat(), (cy - 0.6 *  cy * cos(hourAngle)).toFloat(), paint)

        paint.color = Color.BLUE
        paint.strokeWidth = 15f
        canvas?.drawLine(cx.toFloat(), cy.toFloat(),
            (cx + 0.75 * cx * sin(minAngle)).toFloat(), (cy - 0.75 * cy * cos(minAngle)).toFloat(), paint)

        paint.color = Color.GRAY
        paint.strokeWidth = 10f
        canvas?.drawLine(cx.toFloat(), cy.toFloat(),
            (cx + 0.9 * cx * sin(secAngle)).toFloat(), (cy - 0.95 * cy * cos(secAngle)).toFloat(), paint)

        // 覆盖掉指针柄
        paint.color = Color.BLACK
        canvas?.drawCircle(cx, cy, (0.05 * cx).toFloat(), paint)
    }
}