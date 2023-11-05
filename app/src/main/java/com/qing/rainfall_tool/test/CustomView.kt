package com.qing.rainfall_tool.test

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable

class CustomView : View {
    private var paint: Paint? = null

    //从代码创建视图时使用的简单构造函数。
    constructor(context: Context?) : super(context) {}

    //从XML使用视图时调用的构造函数。
    constructor(context: Context?, @Nullable attrs: AttributeSet?) : super(context, attrs) {}

    //View的绘制工作
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //实例化画笔对象
        paint = Paint()

        //给画笔设置颜色
        paint?.setColor(Color.RED)
        //设置画笔属性
        //paint.setStyle(Paint.Style.FILL);//画笔属性是实心圆
        paint?.setStyle(Paint.Style.STROKE) //画笔属性是空心圆
        paint?.setStrokeWidth(10F) //设置画笔粗细
        //cx：圆心的x坐标;cy：圆心的y坐标;参数三：圆的半径;参数四：定义好的画笔
        canvas.drawCircle((width / 4).toFloat(), (height / 4).toFloat(), 150F, paint!!)
    }


    // customerDialog, AlertDialog
    /*private fun showAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("提示")
        alertDialogBuilder.setMessage("这是一个对话框示例")
        alertDialogBuilder.setPositiveButton("确定") { dialog, which ->
            // 在这里处理确定按钮的点击事件
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("取消") { dialog, which ->
            // 在这里处理取消按钮的点击事件
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }*/

    // Snackbar
    /*
    Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
     */
}