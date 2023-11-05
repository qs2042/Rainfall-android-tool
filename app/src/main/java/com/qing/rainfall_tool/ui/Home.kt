package com.qing.rainfall_tool.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.qing.rainfall_tool.R
import com.qing.rainfall_tool.component.PageRecyclerView


/*
package com.qing.rainfall_tool.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.qing.rainfall_tool.R


class Home : Fragment() {

    companion object {
        val imageList = listOf(
            R.drawable.profile,
            R.drawable.love,
            R.drawable.title
        )
    }

    lateinit var viewPager: ViewPager
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    private fun init(view: View) {
        viewPager = view.findViewById(R.id.viewPager)
        recyclerView = view.findViewById(R.id.recycler_view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        // 轮播图
        val viewPagerAdapter = ViewPagerAdapter(requireActivity(), imageList)
        viewPager.adapter = viewPagerAdapter

        //
        val layoutManager = GridLayoutManager(requireActivity(), 2)
        layoutManager.orientation = GridLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = RecycleAdapter(
            this.requireContext(),
            arrayListOf("景点", "美食", "娱乐", "酒店", "电影票", "周末游", "自驾游", "论坛", "机票", "火车票")
        )

    }

    // 轮播图
    class ViewPagerAdapter(private val context: Context, private val images: List<Int>) :
        PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(context)

            // 简单的做法, 如果需要对图片增加一些功能, 还是使用布局比较好
            */
/*val img = ImageView(context)
            img.setImageResource(images[position])
            img.scaleType = ImageView.ScaleType.CENTER_CROP  // 设置图片的缩放类型为覆盖模式
            container.addView(img)
            return img*//*


            val view = inflater.inflate(R.layout.item_image, container, false)
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            imageView.setImageResource(images[position])
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP  // 设置图片的缩放类型为覆盖模式
            container.addView(view)
            return view


        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun getCount(): Int {
            return images.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }
    }

    class RecycleAdapter(var context: Context, var list: java.util.ArrayList<String>) :
        RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

        // class: viewHolder
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var textView: TextView? = null
            var imageView: ImageView? = null

            init {
                textView = itemView.findViewById<View>(R.id.textView) as TextView
                imageView = itemView.findViewById<View>(R.id.imageView) as ImageView
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // 创建ViewHolder，返回每一项的布局
            val inflater =
                LayoutInflater.from(context).inflate(R.layout.item_image_text, parent, false)
            return ViewHolder(inflater!!)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = list[position]

            // 将数据和控件绑定
            holder.textView?.text = item

            Glide.with(context)
                .load(R.drawable.title)
                // fit: center
                .fitCenter()
                // 通过CircleCrop, 将图片变为圆形
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(holder.imageView!!)
        }

        override fun getItemCount(): Int {
            return list.size
        }
    }

    class GridItemDecoration(spacing: Int, color: Int) : RecyclerView.ItemDecoration() {
        private var paint: Paint = Paint()

        init {
            paint.color = color
            paint.strokeWidth = spacing.toFloat()
        }

        override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        super.onDraw(canvas, parent, state)

            val childCount: Int = parent.childCount
            val spanCount = 2 // 每行格子数量

            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)
                val position = parent.getChildAdapterPosition(child)

                val left = child.left
                val right = child.right
                val top = child.top
                val bottom = child.bottom

                // 绘制垂直方向的线条
                if ((position + 1) % spanCount != 0) {
                    val startX = right.toFloat()
                    val startY = top.toFloat()
                    val stopX = right.toFloat()
                    val stopY = bottom.toFloat()
                    canvas.drawLine(startX, startY, stopX, stopY, paint)
                }

                // 绘制水平方向的线条
                if (position >= spanCount) {
                    val startX = left.toFloat()
                    val startY = top.toFloat()
                    val stopX = left.toFloat()
                    val stopY = bottom.toFloat()
                    canvas.drawLine(startX, startY, stopX, stopY, paint)
                }
            }
        }
    }
}*/

class Home : Fragment() {

    companion object {
        val imageList = listOf(
            R.mipmap.bg_106364616,
            R.mipmap.bg_107651594,
            R.mipmap.bg_87013637
        )
    }

    lateinit var viewPager: ViewPager
    lateinit var recyclerView: RecyclerView

    lateinit var mRecyclerView: PageRecyclerView
    lateinit var dataList: ArrayList<String>
    lateinit var myAdapter: PageRecyclerView.PageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    private fun init(view: View) {
        viewPager = view.findViewById(R.id.viewPager)
//        recyclerView = view.findViewById(R.id.recycler_view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        dataList = ArrayList()
        for (i in 0..49) {
            dataList.add(i.toString())
        }

        // 轮播图
        val viewPagerAdapter = ViewPagerAdapter(requireActivity(), imageList)
        viewPager.adapter = viewPagerAdapter

        mRecyclerView = view.findViewById(R.id.cusom_swipe_view);
        // 设置指示器
        mRecyclerView.setIndicator(view.findViewById(R.id.indicator));
        // 设置行数和列数
        mRecyclerView.setPageSize(2, 5);
        // 设置页间距
        mRecyclerView.setPageMargin(5)
        // 动画
        /*val myAnim = AnimationUtils.loadAnimation(this as Context, R.anim.in_from_right)
        myAnim.fillAfter = true;//android动画结束后停在结束位置
        val set = AnimationSet(false)
        set.addAnimation(myAnim);    //加入动画集合
        val controller = LayoutAnimationController(set, 1.0f)
        mRecyclerView.setLayoutAnimation(controller)*/

        //
//        mRecyclerView.setLayoutManager(new AutoGridLayoutManager(RecycleViewActivity.this,dataList.size()));
        // 设置数据
        val callback = object : PageRecyclerView.CallBack {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                // 在这里创建和返回你的ViewHolder
                val view = LayoutInflater.from(this@Home.context).inflate(R.layout.item_image_text, parent, false);
                return MyHolder(view);
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                // 在这里绑定数据到你的ViewHolder
                val holder = holder as MyHolder
                holder.tv.text = dataList[position]

                holder.tv.visibility = View.GONE

                /*Glide.with(this@Home)
                    .load(R.drawable.title)
                    // fit: center
                    .fitCenter()
                    // 通过CircleCrop, 将图片变为圆形
                    .apply(RequestOptions.bitmapTransform(CircleCrop()))
                    .into(holder.iv)*/
            }
        }
        mRecyclerView.adapter = mRecyclerView.PageAdapter(dataList, callback)

    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView
        var iv: ImageView

        init {
            this.tv = itemView.findViewById(R.id.textView)
            this.iv = itemView.findViewById(R.id.imageView)
            /*tv.setOnClickListener {
                Toast.makeText(this@Home as Context, adapterPosition, Toast.LENGTH_SHORT).show();
            }
            tv.setOnLongClickListener {
                myAdapter.remove(adapterPosition)
                return true;
            }*/
        }
    }

    // 轮播图
    class ViewPagerAdapter(private val context: Context, private val images: List<Int>) :
        PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(context)

            // 简单的做法, 如果需要对图片增加一些功能, 还是使用布局比较好
            /*val img = ImageView(context)
            img.setImageResource(images[position])
            img.scaleType = ImageView.ScaleType.CENTER_CROP  // 设置图片的缩放类型为覆盖模式
            container.addView(img)
            return img*/

            val view = inflater.inflate(R.layout.item_image, container, false)
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            imageView.setImageResource(images[position])
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP  // 设置图片的缩放类型为覆盖模式
            container.addView(view)
            return view


        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun getCount(): Int {
            return images.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }
    }

    class RecycleAdapter(var context: Context, var list: java.util.ArrayList<String>) :
        RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

        // class: viewHolder
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var textView: TextView? = null
            var imageView: ImageView? = null

            init {
                textView = itemView.findViewById<View>(R.id.textView) as TextView
                imageView = itemView.findViewById<View>(R.id.imageView) as ImageView
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // 创建ViewHolder，返回每一项的布局
            val inflater =
                LayoutInflater.from(context).inflate(R.layout.item_image_text, parent, false)
            return ViewHolder(inflater!!)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = list[position]

            // 将数据和控件绑定
            holder.textView?.text = item

            Glide.with(context)
                .load(R.drawable.title)
                // fit: center
                .fitCenter()
                // 通过CircleCrop, 将图片变为圆形
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(holder.imageView!!)
        }

        override fun getItemCount(): Int {
            return list.size
        }
    }

    class GridItemDecoration(spacing: Int, color: Int) : RecyclerView.ItemDecoration() {
        private var paint: Paint = Paint()

        init {
            paint.color = color
            paint.strokeWidth = spacing.toFloat()
        }

        override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        super.onDraw(canvas, parent, state)

            val childCount: Int = parent.childCount
            val spanCount = 2 // 每行格子数量

            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)
                val position = parent.getChildAdapterPosition(child)

                val left = child.left
                val right = child.right
                val top = child.top
                val bottom = child.bottom

                // 绘制垂直方向的线条
                if ((position + 1) % spanCount != 0) {
                    val startX = right.toFloat()
                    val startY = top.toFloat()
                    val stopX = right.toFloat()
                    val stopY = bottom.toFloat()
                    canvas.drawLine(startX, startY, stopX, stopY, paint)
                }

                // 绘制水平方向的线条
                if (position >= spanCount) {
                    val startX = left.toFloat()
                    val startY = top.toFloat()
                    val stopX = left.toFloat()
                    val stopY = bottom.toFloat()
                    canvas.drawLine(startX, startY, stopX, stopY, paint)
                }
            }
        }
    }
}