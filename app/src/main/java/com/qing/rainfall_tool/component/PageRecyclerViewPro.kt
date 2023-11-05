package com.qing.rainfall_tool.component

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

// class PageRecyclerViewPro @JvmOverloads constructor(
class PageRecyclerViewPro constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    constructor (context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    /*constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) {
        super(context, attrs, defStyle)
        defaultInit(context)
    }*/

}