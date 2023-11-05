package com.qing.rainfall_tool.ui

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.qing.rainfall_tool.MainActivity
import com.qing.rainfall_tool.R
import com.qing.rainfall_tool.ui.functions.*
import com.qing.rainfall_tool.utils.GlideUtil

// pojo
class Menu(var iconResId: Int?, val text: String, val cls: Class<*>?) {
    init {
        if (iconResId == -1 || iconResId == null) {
            iconResId = Functions.activity?.resources?.getIdentifier(
                "title",
                "drawable",
                Functions.activity?.packageName
            )!!
        }
    }

    companion object {
        var number: Int = 0

        @JvmStatic
        fun getFakeData(int: Int): java.util.ArrayList<Menu> {
            val list = java.util.ArrayList<Menu>()
            for (i in 0..int) {
                val resourceId = Functions.activity?.resources?.getIdentifier(
                    "title",
                    "drawable",
                    Functions.activity?.packageName
                )
                list.add(
                    Menu(
                        resourceId,
                        "奥尔良鸡腿$i，美味鲜香，快来试试！便宜实惠又健康，还在等待什么呢！！！！",
                        null
                    )
                )
            }
            return list
        }
    }
}

// adapter
open class RecycleAdapter(context: Context, list: java.util.ArrayList<Menu>) :
    RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    private var context: Context? = null
    private var list: List<Menu>? = null

    init {
        this.list = list
        this.context = context
    }

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
        val item = list?.get(position)

        // 将数据和控件绑定
        holder.textView?.text = item?.text
        if (item?.cls == null) {
            holder.textView?.paint?.flags = Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.textView?.paint?.flags = Paint.FAKE_BOLD_TEXT_FLAG
        }

        holder.imageView?.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }

//        holder.imageView?.setImageResource(item?.iconResId!!)
//        holder.imageView?.layoutParams?.height = holder.imageView!!.width
        Glide.with(context!!)
            .load(item?.iconResId!!)
            // fit: center
            .fitCenter()
            // 通过自定义类, 将图片设置为黑白(不缓存转换后的图像)
            /*.apply(
                RequestOptions.bitmapTransform(GlideUtils.BlackWhiteTransformation())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
            )*/
            // 通过CircleCrop, 将图片变为圆形
//            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            // 融合上面的俩个apply, 注意apply只能有一个, 所以需要合并一下apply
            .apply(
                RequestOptions.bitmapTransform(GlideUtil.BlackWhiteTransformation())
                    //
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    //
                    .transform(CircleCrop())
            )

            .into(holder.imageView!!)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    // 创建OnItemClickListener接口
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        onItemClickListener = listener
    }
}

// decoration
class GridItemDecoration(private val spacing: Int, private val color: Int) :
    RecyclerView.ItemDecoration() {
    private val paint = Paint()

    init {
        paint.color = color
        paint.strokeWidth = spacing.toFloat()
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        super.onDraw(canvas, parent, state)

        val childCount: Int = parent.childCount
        val spanCount = 3 // 每行格子数量

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)

            val left = child.left
            val right = child.right
            val top = child.top
            val bottom = child.bottom

            // 绘制垂直方向的线条
            if (position % spanCount != spanCount - 1) {
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
                val stopX = right.toFloat()
                val stopY = top.toFloat()
                canvas.drawLine(startX, startY, stopX, stopY, paint)
            }
        }
    }
}

// fragment
class Functions : Fragment(), RecycleAdapter.OnItemClickListener {
    // member
    private lateinit var activity: MainActivity
    private var recyclerView: RecyclerView? = null

    // static
    companion object {
        var activity: MainActivity? = null
        var list: ArrayList<Menu> = ArrayList()
    }

    // pojo
    // adapter
    // decoration

    // code
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_functions, container, false)
        Companion.activity = requireActivity() as MainActivity
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 获取recyclerView
        recyclerView = view.findViewById(R.id.recycler_view)

        initMenu()

        // 初始化数据
        initRecyclerView()
    }

    private fun initMenu() {
        list = arrayListOf(
            Menu(-1, "设备", Device::class.java),

            // 网络 TODO: 代理
            Menu(-1, "自定义hosts", HostsManager::class.java),
            Menu(-1, "IP/域名查询", IpSearch::class.java),
            Menu(-1, "端口扫描", PortScan::class.java),
            Menu(-1, "网络诊断", NetworkDiagnostic::class.java),
            Menu(-1, "WIFI工具", WiFiTool::class.java),
            // 功能
            Menu(-1, "地图", RMap::class.java),
            Menu(-1, "扫一扫", CodeScan::class.java),
            Menu(-1, "文件管理", FileManager::class.java),
            Menu(-1, "音视频下载", ResourcesDownload::class.java),
            Menu(-1, "历史上的今天", null),
            Menu(-1, "浏览器", null),
            Menu(-1, "文章生成器", null),
            Menu(-1, "进制转换", null),

            Menu(-1, "QQ模块", null),
            Menu(-1, "闹钟模块", null),
            Menu(-1, "监控模块", null),
            Menu(-1, "笔记模块", null),
            Menu(-1, "论坛模块", WrappedH5::class.java),
            Menu(-1, "测试模块", null),
            Menu(-1, "文游模块", null)
        )
    }

    private fun initRecyclerView() {
        // 创建适配器
        val adapterDome = RecycleAdapter(requireActivity(), list)

        // 配置点击事件
        adapterDome.setOnItemClickListener(this)

        // 配置瀑布流布局管理器(设置列数为3)
//        val managerStagger = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        // 配置网格流布局管理器
        val gridManager = GridLayoutManager(requireActivity(), 3)

        // 配置装饰
        val spacing = resources.getDimensionPixelSize(R.dimen.grid_spacing_min)
//        val lineColor = resources.getColor(R.color.grey)
        val lineColor = ContextCompat.getColor(requireActivity(), R.color.grey)
        val itemDecoration = GridItemDecoration(spacing, lineColor)

        // 赋值给适配器
        recyclerView?.layoutManager = gridManager
        recyclerView?.adapter = adapterDome
        recyclerView?.addItemDecoration(itemDecoration)
    }

    override fun onItemClick(position: Int) {
        val item = list[position]

        if (item.cls == null) {
            Toast.makeText(requireActivity(), "功能暂未完成", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(requireActivity(), item.cls)
//        intent.putExtra(RMap.EXTRA_NAME, "测试标题")
        startActivity(intent)
        /*Toast.makeText(
            requireActivity(),
            "Hello, ${item.text}!",
            Toast.LENGTH_SHORT
        ).show()*/
    }
}