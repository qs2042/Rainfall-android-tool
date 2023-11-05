package com.qing.rainfall_tool.layout

import android.content.Context
import android.graphics.PointF
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class CarouselGridLayoutManager(context: Context?) :
    GridLayoutManager(context, SPAN_COUNT) {
    override fun canScrollHorizontally(): Boolean {
        return false // 禁用水平滚动
    }

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State,
        position: Int
    ) {
        val smoothScroller: LinearSmoothScroller =
            object : LinearSmoothScroller(recyclerView.context) {
                override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
                    val row = targetPosition / SPAN_COUNT
                    val page = row / PAGE_SIZE
                    val column = targetPosition % SPAN_COUNT
                    val deltaX = ((page * SPAN_COUNT + column) * getDecoratedMeasuredWidth(
                        getChildAt(0)!!
                    )).toFloat()
                    return PointF(deltaX, 0F)
                }
            }
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    companion object {
        private const val SPAN_COUNT = 5 // 每行显示的项数
        private const val PAGE_SIZE = 2 // 每列显示的项数
    }
}