# 前置

## Java

## Kotlin

## Android

# 页面

## 结构

```text
*   表示还有子类没有学习
?   表示当前的类没有学习

┌ View
├──┼ TextureView(?)
├──┼ ViewStub(?)
├──┼ SurfaceView(?)
├──┼ ProgressBar(?)
├──┼ Space(?)
├──┼ ConstraintHelper(?)
├──┼ ViewStubCompat(?)
├──┼ MediaRouteButton(?)
├──┼ KeyboardView(?)
├──┼ Placeholder(?)
├──┼ MockView(?,*)
├──┼ AnalogClock(?)
├──┼ Guildeline(?)
├──┼ ImageView
│  │  ├ ImageButton(*)
│  │  ├ AppCompatImageView(*)
│  │  └ QuickContactBadge
├──┼ ViewGroup
│  │  ├ DrawerLayout
│  │  ├ CoordinatorLayout
│  │  ├ ViewPager(?)
│  │  ├ ViewPager2(?)
│  │  ├ InlineContentView(?)
│  │  ├ SlidingDrawer(?)
│  │  ├ TVView(?)
│  │  ├ ActionBarOverlayLayout(?)
│  │  ├ FragmentBreadCrumbs(?)
│  │  ├ ActivityChooserView(?)
│  │  ├ Constraints(?)
│  │  ├ LinearLayout(*)
│  │  ├ FrameLayout(*)
│  │  ├ RecyclerView(*)
│  │  ├ RelativeLayout(*)
│  │  ├ LinearLayoutCompat(?,*)
│  │  ├ PagerTitleStrip(?,*)
│  │  ├ AbsoluteLayout(?,*)
│  │  ├ Toolbar(?,*)
│  │  ├ AbsActionBarView(?,*)
│  │  ├ ConstraintLayout(?,*)
│  │  ├ GridLayout
│  │  ├ TableLayout
│  │  ├ RelativeLayout
│  │  ├ AdapterView
│  │  │  ├ AdapterViewAnimator(?,*)
│  │  │  ├ AbsSpinner(?,*)
│  │  │  └ AbsListView
│  │  │     ├ ListView
│  │  │     └ GridView
│  │  └ FrameLayout
│  │     └ ScrollView
│  └ TextView
│    ├ AppCompatTextView(?,*)
│    ├ DigitalClock(?)
│    ├ TextClock(?)
│    ├ Chronometer(?)
│    ├ CheckedTextView(?,*)
│    ├ EditText
│    └ Button
│      ├ AppCompatButton
│      └ CompoundButton
│         ├ Switch
│         ├ CheckBox(*)
│         ├ SwitchCompat(?)
│         ├ RadioButton(*)
│         └ ToggleButton(*)
├──── 
├──── 
```

## Activity

demo/MyActivity.kt

## Fragment

demo/MyFragment.kt

## 布局类

### 通用属性

```text

[设置视图的内边距]
android:padding
android:paddingTop
android:paddingBottom
android:paddingLeft
android:paddingRight
```

### 线性布局(LinearLayout)

按照水平或垂直方向排列子元素。

```xml
<!-- 
android:orientation     指定子视图的排列方向，可设置为"horizontal"（水平）或"vertical"（垂直）。
android:layout_weight   指定子视图的权重，用于实现多个子视图之间的分布比例。
android:gravity         指定子视图的对齐方式，可设置为"top"、"bottom"、"left"、"right"、"center_vertical"、"center_horizontal"等。
-->
<LinearLayout
    android:orientation="vertical">

    <Component />
    <Component />

</LinearLayout>
```

### 相对布局(RelativeLayout)

通过相对位置定义子元素的位置关系。

```xml
<!-- 
[指定子视图相对于父视图的对齐方式]
android:layout_alignParentTop
android:layout_alignParentBottom
android:layout_alignParentLeft
android:layout_alignParentRight

[指定子视图相对于其他视图的位置]
android:layout_above
android:layout_below
android:layout_toLeftOf
android:layout_toRightOf

[指定子视图在父视图中居中显示]
android:layout_centerInParent

[指定子视图与其他视图的边对齐]
android:layout_alignTop
android:layout_alignBottom
android:layout_alignLeft
android:layout_alignRight

[指定子视图与周围视图之间的边距]
android:layout_margin
android:layout_marginTop
android:layout_marginBottom
android:layout_marginLeft
android:layout_marginRight
-->
<RelativeLayout >

    <Component
        android:id="@+id/a"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true" />

    <Component
        android:id="@+id/b"
        android:layout_below="@id/textView1"
        android:layout_centerHorizontal="true" />

</RelativeLayout>
```

### 帧布局(FrameLayout)

子元素可以叠加显示。

```xml
<!-- 
[设置前景视图，可以是颜色、Drawable或其他视图]
android:foreground

[设置前景视图的对齐方式]
android:foregroundGravity: "top"、"bottom"、"left"、"right"、"center_vertical"、"center_horizontal"

[设置子视图的对齐方式]
android:layout_gravity: "top"、"bottom"、"left"、"right"、"center_vertical"、"center_horizontal"

[设置子视图的外边距对齐方式]
android:layout_marginGravity: "top"、"bottom"、"left"、"right"、"center_vertical"、"center_horizontal"

[设置是否在测量子视图时同时测量所有子视图]
android:measureAllChildren

 -->
<FrameLayout >

    <Component/>

    <Component/>

</FrameLayout>
```

### 约束布局(ConstraintLayout)

通过设置约束条件定义子元素的位置关系。

```xml
<!-- 
[设置视图相对于其他视图或父视图的顶部、底部、开始、结束位置]
app:layout_constraintTop_toTopOf
app:layout_constraintBottom_toBottomOf
app:layout_constraintStart_toStartOf
app:layout_constraintEnd_toEndOf

[设置链式布局中的视图对齐方式]
app:layout_constraintVertical_chainStyle
app:layout_constraintHorizontal_chainStyle

[设置视图在垂直或水平方向上的偏移量]
app:layout_constraintVertical_bias
app:layout_constraintHorizontal_bias

[设置视图宽度或高度相对于父视图宽度或高度的百分比]
app:layout_constraintWidth_percent
app:layout_constraintHeight_percent

[设置视图宽度和高度的比例关系]
app:layout_constraintDimensionRatio

[定义一个百分比位置的辅助线，用于对齐视图]
app:layout_constraintGuide_percent
 -->
<ConstraintLayout>

    <Component
        android:id="@+id/a"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <Component
        android:id="@+id/b"
        app:layout_constraintTop_toBottomOf="@+id/a"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</ConstraintLayout>
```

### 表格布局(TableLayout)

用于创建表格结构。

```xml
<!-- 
android:collapseColumns     定义要折叠的列，可以指定一个或多个列的索引。
android:shrinkColumns       定义要收缩的列，可以指定一个或多个列的索引。
android:stretchColumns      定义要拉伸的列，可以指定一个或多个列的索引。
 -->
<TableLayout>

    <TableRow>
        <Component />
        <Component />
    </TableRow>

    <TableRow>
        <Component />
        <Component />
    </TableRow>

</TableLayout>
```

### 网格布局(GridLayout)

将子元素放置在规则的网格中。

```xml
<!-- 
android:columnCount         指定网格布局的列数。
android:rowCount            指定网格布局的行数。
android:orientation         指定网格布局的方向，可以是水平方向（"horizontal"）或垂直方向（"vertical"）。
android:useDefaultMargins   指定是否使用默认的外边距。
 -->
<GridLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:columnCount="2">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Item 1"
        android:layout_column="0"
        android:layout_row="0" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Item 2"
        android:layout_column="1"
        android:layout_row="0" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Item 3"
        android:layout_column="0"
        android:layout_row="1" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Item 4"
        android:layout_column="1"
        android:layout_row="1" />

</GridLayout>
```

### 复杂布局(CoordinatorLayout)

可用于实现复杂的交互效果和协调子视图之间的行为。

```xml
<!-- 
app:layout_anchor           指定一个视图作为锚点，用于定位其他视图的位置。
app:layout_anchorGravity    指定视图的对齐方式，以相对于锚点的位置来定义。
app:layout_behavior         指定一个Behavior类，用于控制视图的交互行为。
app:statusBarBackground     指定状态栏的背景颜色。
app:layout_dodgeInsetEdges  指定视图是否应该避开指定的Inset边缘。
 -->
<androidx.coordinatorlayout.widget.CoordinatorLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Button"
        app:layout_behavior="com.example.MyBehavior" />

    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/my_image"
        app:layout_anchor="@id/button"
        app:layout_anchorGravity="bottom|end" />

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

### 抽屉式布局(DrawerLayout)

通常用于创建侧滑菜单和导航功能

```xml
<!-- 
android:layout_gravity  指定抽屉视图的位置，可以是"start"、"end"、"left"、"right"。
android:drawerLockMode  指定抽屉锁定模式，可以是"unlocked"、"locked"、"lockedOpen"、"lockedClosed"。
android:drawerElevation 指定抽屉的高度。
android:scrimColor      指定抽屉打开时的遮罩颜色。
android:drawerTitle     指定抽屉的标题。
 -->
<androidx.drawerlayout.widget.DrawerLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <!-- 主内容视图 -->

    </LinearLayout>

    <LinearLayout
        android:layout_width="240dp"
        android:layout_height="match_parent"
        android:layout_gravity="start">

        <!-- 抽屉视图 -->

    </LinearLayout>

</androidx.drawerlayout.widget.DrawerLayout>
```

### ViewStub(布局延迟加载的占位符视图)

**xml**
```xml
<ViewStub
    android:id="@+id/my_view_stub"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_toLeftOf="@id/other_view"
    android:inflatedId="@+id/my_inflated_view"
    android:layout="@layout/my_large_layout" />
```

**activity**

```kotlin
// 只有在my_view_stub视图被Inflated时，才会加载这个复杂的布局。否则，它会被完全忽略。
val viewStub = findViewById(R.id.my_view_stub)
// 获取并将XML转为View对象
val inflatedView = viewStub.inflate()
// 将其添加到布局中
val layout = findViewById(R.id.my_layout)
layout.addView(inflatedView);
// 不需要了, 就将其隐藏
inflatedView.setVisibility(View.GONE)
```

## 视图容器类

### SurfaceView(用于在后台线程中进行绘制)

```xml
<LinearLayout>
    <SurfaceView android:id="@+id/surfaceView" />
</LinearLayout>
```

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var surfaceView: SurfaceView
    private var random: Random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        surfaceView = findViewById(R.id.surfaceView)
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback() {
            override fun surfaceCreated(holder: SurfaceHolder) {
                drawOnSurface()
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                // Not implemented
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                // Not implemented
            }
        })
    }

    private fun drawOnSurface() {
        surfaceView.canvas?.let { canvas ->
            val paint = Paint()
            paint.color = Color.BLUE
            canvas.drawRect(50f, 50f, 200f, 200f, paint)
            canvas.drawCircle(100f, 100f, 50f, paint)
            canvas.drawLine(50f, 50f, 100f, 100f, paint)
        }
    }
}
```

### TextureView(支持硬件加速的可旋转、缩放和透明度等操作)

一个TextureView可以用来显示一个内容流。 这样的内容流例如可以是视频或OpenGL场景。 

内容流可以来自应用程序的流程以及远程流程。

TextureView只能用于硬件加速窗口。 

当用软件渲染时，TextureView将不绘制任何东西。

与SurfaceView不同的SurfaceView ，TextureView不会创建一个单独的窗口，但其行为与常规视图相同。 

这个关键区别允许TextureView被移动，转换，动画等。

例如，您可以通过调用myView.setAlpha(0.5f)来制作TextureView半透明。

使用TextureView很简单：你需要做的就是获得它的SurfaceTexture 。 

然后可以使用SurfaceTexture呈现内容。 

以下示例演示如何将相机预览渲染到TextureView中

```xml
<LinearLayout>
     <TextureView android:id="@+id/textureView" />
</LinearLayout>
```

```java
public class LiveCameraActivity extends Activity implements TextureView.SurfaceTextureListener {
  private Camera mCamera;
  private TextureView mTextureView;

  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      mTextureView = new TextureView(this);
      mTextureView.setSurfaceTextureListener(this);

      setContentView(mTextureView);
  }

  public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
      mCamera = Camera.open();

      try {
          mCamera.setPreviewTexture(surface);
          mCamera.startPreview();
      } catch (IOException ioe) {
          // Something bad happened
      }
  }

  public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
      // Ignored, Camera does all the work for us
  }

  public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
      mCamera.stopPreview();
      mCamera.release();
      return true;
  }

  public void onSurfaceTextureUpdated(SurfaceTexture surface) {
      // Invoked every time there's a new Camera preview frame
  }
}
```

### KeyboardView(显示自定义键盘)

### MediaRouteButton(显示媒体路由按钮)

### ViewPager(用于实现左右滑动切换不同页面的容器)

### ExpandableListView(可展开和折叠的列表视图，用于显示分组数据)

### Gallery(画廊视图，用于水平滑动查看图片集合)

### HorizontalScrollView(水平滚动的视图容器)

```xml
<HorizontalScrollView>
    <LinearLayout>
        <ComponentView/>
        <ComponentView/>
        <ComponentView/>
        <ComponentView/>
        <ComponentView/>
    </LinearLayout>
</HorizontalScrollView>
```

### ScrollView(可滚动的视图容器)

用于显示超过屏幕尺寸的内容。

```xml
<ScrollView>
    <LinearLayout>
        <ComponentView/>
        <ComponentView/>
        <ComponentView/>
        <ComponentView/>
        <ComponentView/>
    </LinearLayout>
</ScrollView>
```

## 视图容器类(Adapter)

### 介绍

开发新的应用程序或者需要实现复杂的列表和网格布局，并希望享受更好的性能和灵活性，推荐使用 RecyclerView.Adapter

如果是维护旧的应用程序或者需要快速实现简单的列表视图，可以选择使用 BaseAdapter。

### AdapterView

继承了AdapterView的View, 都可用来搭配适配器展示数据

常用的有: ListView, GridView, Spinner, ExpandableListView

|key|value|adapter|
|:---:|:---:|:---:|
| AbsListView               | 抽象父类，是ListView和GridView的基类，用于展示可滚动的列表或网格布局。
| AbsSpinner                | 抽象父类，是Spinner的基类，用于展示下拉选择框。
| Spinner                   | 是下拉列表视图     | BaseAdapter
| Gallery                   | 横向滚动的画廊视图 | BaseAdapter(Android 4.4及以后的版本中已被废弃)
| AdapterViewFlipper        | 自动滚动的视图容器 | BaseAdapter
| ExpandableListView        | 可展开的列表视图   | BaseExpandableListAdapter(BaseAdapter的子类)
| AdapterViewAnimator       | 可切换多个视图的容器，可以根据适配器的数据切换不同的视图。
| AppCompatSpinner          | 兼容性支持库中的Spinner，用于展示下拉选择框。
| DropDownListView          | 用于下拉列表的ListView，常用于PopupWindow等弹出窗口中。
| ExpandedMenuView          | 用于显示扩展后的菜单视图，用于弹出菜单中的子菜单。
| GridView                  | 网格视图，用于展示数据以网格形式排列的布局。
| ListView                  | 列表视图，用于展示可滚动的垂直列表布局。
| MaterialCalendarGridView  | Material Design风格的日历网格视图。
| MenuDropDownListView      | 用于菜单弹出窗口中的下拉列表视图。
| RecycleListView           | 用于AlertController中的列表视图，用于显示对话框中的列表内容。
| Spinner                   | 下拉选择框，用于从预定义选项中选择一个值。
| StackView                 | 堆叠视图，可以通过手势在堆叠的视图之间进行滑动和切换。

### BaseAdapter

这是一个抽象类，为开发者提供了自定义适配器的基本框架。

开发者需要继承BaseAdapter类，并根据自己的需求实现其中的方法

例如getCount()、getItem()、getItemId()和getView()，来定义数据的获取和视图的创建。

不同于RecyclerView.Adapter，BaseAdapter需要自己将Holder绑定到Adapter类中

在getView(position, convertView, parent)方法中, 通过判断convertView是否为空, 就知道是否绑定Holder了

如果为空那就创建Holder类, 将查找到的视图保存在Holder类中

然后通过convertView的setTag方法进行保存 `convertView.setTag(holder)`

这样子当第二次访问的时候, convertView就不为NULL了, 避免了重复查找视图的开销

### ArrayAdapter

BaseAdapter的子类, 顾名思义就是接收一个数据集合对象作为输入，并使用默认的布局资源显示每个数据项。

用于将数据集合绑定到ListView或Spinner等可滚动列表视图。

如果你的数据不是ArrayList, 也可以通过继承BaseAdapter, 自定义出一个 CustomerAdapter 类出来

### RecyclerView.Adapter

用于将数据集合绑定到RecyclerView。

它类似于BaseAdapter，但更为特殊, 它具有更强大和灵活的功能。

开发者需要继承RecyclerView.Adapter类，并实现其中的方法

例如getItemCount()、onCreateViewHolder()和onBindViewHolder()，来定义数据的获取和视图的创建与绑定。

融合配合 RecyclerView 视图进行使用

```kotlin
class MyAdapter(
    var context: Context, var list: java.util.ArrayList<Map>
) : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

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
            LayoutInflater.from(context).inflate(R.layout.grid_item_layout, parent, false)
        return ViewHolder(inflater!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list?.get(position)

        // 将数据和控件绑定
        holder.textView?.text = item?.text

        holder.imageView?.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }

        Glide.with(context!!)
            .load(item?.iconResId!!)
            .fitCenter()
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
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
```

GridLayoutManager, LinearLayoutManager, StaggeredGridLayoutManager

### GridView(用于显示网格布局的列表，可用于展示图片、图标等)

```xml
<GridView
    android:numColumns="3"
    android:horizontalSpacing="5dp"
    android:verticalSpacing="5dp" />
```

**numColumns**
几列的意思

**horizontalSpacing**
水平间距

**verticalSpacing**
垂直间距

**Adapter**

BaseAdapter, ArrayAdapter

### ListView(用于显示垂直滚动的列表，可用于展示大量数据)

```xml
<ListView android:listSelector="@drawable/selector_list_item" />
```

**divider**

分割线颜色/样式

**dividerHeight**

分割线高度

**listSelector**

列表选中, 可通过selector来切换选中和未选中的样式

### RecyclerView(用于高效显示大量数据的列表或网格)

**XML**

```xml
<RelativeLayout>
    <RecyclerView/>
</RelativeLayout>
```

**视图管理器**

```kotlin
// 线性(垂直)
val layoutManager = new LinearLayoutManager(this)
//layoutManager.setOrientation(LinearLayoutManager.VERTICAL)

// 线性(水平)
val layoutManager = new LinearLayoutManager(this)
layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL)

// 瀑布流布局
val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

// 网格
val layoutManager = GridLayoutManager(this, 3)

// 瀑布流
val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

RecyclerView.setLayoutManager(layoutManager)
```

**ItemDecoration**

```kotlin
class MyDecoration: RecyclerView.ItemDecoration {
    // onDraw         数据绘画之前绘制内容(神奇的PS图层理解方法 : 相当于在人物底下画背景)
    // onDrawOver     数据绘画之后绘制内容(神奇的PS图层理解方法 : 相当于在人物上面画背景)
    // getItemOffsets 周边绘制内容
    @Override
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State){
        super.getItemOffsets(outRect, view, parent, state);
        // 在底部绘制1dp空白图层
        outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        
        // 绘制5dp的间距
        var gap = getResources().getDimensionPixelSize(R.dimen.dividerHeight5);
        outRect.set(gap,gap,gap,gap);

    }
}

RecyclerView.addItemDecoration(MyDecoration())
```

### XRecyclerView

XRecyclerView在RecyclerView的基础上添加了下拉刷新、上拉加载更多、添加HeaderView和FooterView等功能。

0. 下拉刷新和上拉加载更多：XRecyclerView内置了下拉刷新和上拉加载更多的功能，可以轻松实现列表的刷新和加载更多数据。你可以自定义刷新和加载更多的样式和行为。
0. 自动加载更多：XRecyclerView支持自动加载更多数据，当滚动到列表底部时自动触发加载更多操作。
0. 添加HeaderView和FooterView：XRecyclerView允许在列表顶部和底部添加HeaderView和FooterView，使你可以在列表中插入自定义的内容。
0. 分割线和动画效果：XRecyclerView支持自定义分割线样式和动画效果，可以为列表项之间添加分隔线，并为列表项的添加和删除等操作提供动画效果。
0. 空布局和错误布局：XRecyclerView提供了空布局和错误布局的支持，当列表数据为空或加载错误时，可以显示自定义的空布局或错误布局。
0. 扩展性：XRecyclerView提供了丰富的回调接口和事件监听器，方便你根据需要进行扩展和定制。

## 视图类

### 文本(TextView)

### 编辑框(EditText)

```xml
<EditText
    android:background="@drawable/background_edittext"
    android:drawableLeft="@drawable/icon_name48"
    android:drawablePadding="5dp"
    android:hint="Name"
    android:maxLines="1"
    android:paddingLeft="10dp"
    android:paddingRight="10dp"
    android:textColor="@color/Red"
    android:textSize="15sp" />
```

**事件(文本改变)**

EditText.addTextChangedListener

0. beforeTextChanged(文字改变之前)
0. onTextChanged(文字改变当中)
0. afterTextChanged(文字改变之后)

### 按钮(Button)

```xml
<Button
    android:onClick="ShowToast"
    android:background="#FF0000"
    android:text="背景色,字体颜色,字体大小"
    android:textColor="#FFFFFF"
    android:textSize="25sp" />
```

onClick     绑定点击事件, 对应activity内的函数, 不过一般是在activity里绑定的, 而不是通过xml属性

### 选项框(RadioButton)

```xml
<RadioGroup android:orientation="horizontal|vertical">
   <RadioButton 
       android:background="@drawable/selector_orange"
       android:text="Boy" />

   <RadioButton
       android:button="@null"
       android:gravity="center"
       android:text="Girl" />

</RadioGroup>
```

注: RadioGroup较为特殊, 继承LinearLayout, 属于布局类

RadioGroup.setOnCheckedChangeListener 监听事件, 会提供俩个参数group和checkedId

通过group控件对象, findViewById获取checkedId, 就可以知道用户选择了那一个选项

### 选择框(CheckBox)

```xml
<!-- 可以通过button属性 + selector, 自定义选中的图标状态 -->
<CheckBox android:text="Android" android:button="@drawable/selector_checkbox" />
```

CheckBox.setOnCheckedChangeListener 选中改变事件

事件中有一个函数onCheckedChanged, 有俩个参数buttonView和isChecked

### 图像(ImageView)

```xml
<ImageView
        android:background="#FF9900"
        android:scaleType="[scaleType]"
        android:src="@drawable/bid_14215405"
        tools:layout_editor_absoluteX="16dp" />
```

**scaleType**

0. fitXY 图片撑满控件,宽高比会发生改变
0. fitCenter 保持高宽比缩放,直至能够完全显示
0. centerCrop 保持高宽比缩放,直至完全覆盖,裁剪显示

**other**

除了android自带的方法, 还可以通过Glide加载图片

Glide对原本的方法进行了包装, 不仅可以加载本地图片还能加载网络图片, 且会解决一些错误

Glide.with(this).load(MyUrl).into(MyImageView4);

### AnalogClock(模拟时钟)

### space(用于占据空间但不显示任何内容)

### 进度条(ProgressBar: 自定义)

**xml**

```xml
<ProgressBar
    style="@style/MyProgressBar"
    android:indeterminateDrawable="@drawable/bg_progress" />
```

**style**

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="MyProgressBar">
        <!--<item name="android:indeterminateDrawable">@drawable/icon_progress</item>-->
        <item name="android:indeterminateDrawable">@drawable/bg_progress</item>
    </style>
</resources>
```

**indeterminateDrawable**

```xml
<?xml version="1.0" encoding="utf-8"?>
    <!-- icon_progress: 准备一个图标, 例如小黑点绕城圆, 小黑点从大到小 -->
<animated-rotate xmlns:android="http://schemas.android.com/apk/res/android"
    android:drawable="@drawable/icon_progress"
    android:pivotX="50%"
    android:pivotY="50%">

</animated-rotate>
```

### 进度条(ProgressBar)

**xml**

```xml
<LinearLayout>
    <ProgressBar android:visibility="visible" style="@android:style/Widget.Material.ProgressBar" />
    <ProgressBar style="@android:style/Widget.ProgressBar" />
    <ProgressBar style="@android:style/Widget.ProgressBar.Horizontal"
        android:max="100"
        android:progress="10" />
    <ProgressBar style="@android:style/Widget.Material.ProgressBar.Horizontal"
        android:max="100"
        android:progress="10"
        android:secondaryProgress="20" />
</LinearLayout>
```

**visibility**

```text
gone     不可见
invisible不可见(位置还在)
visible  可见  (位置还在)
```

**style**

```text
Default = "@android:style/Widget.Material.ProgressBar"
Old     = "@android:style/Widget.ProgressBar"
```

**method**

```kotlin
ProgressBar.setProgress(30)
```

**案例**

假设有一个进度条View(ProgressBar), 和一个按钮View(Button)

```kotlin
val handler = Handler(Looper.getMainLooper()){
    override fun handleMessage(@NonNull msg: Message) {
        super.handleMessage(msg);
        if(ProgressBar.getProgress() < 100){
            // 延迟500毫秒调用runnable
            MyHandler.postDelayed(runnable, 500)
        }else{
            // 加载完成
        }
    }
}
val runnable = Runnable() {
    override fun run() {
        ProgressBar.setProgress(ProgressBar.getProgress() + 5);
        handler.sendEmptyMessage(0);
    }
}


Button.setOnClickListener {
    override fun onClick(v: View) {
        handler.sendEmptyMessage(0);
        // 给handler发送一个消息0
        // handler检测进度条小于100 → 延迟500毫秒调用runnable
        // runnable给进度条+5,然后再给runnable发送一个消息0
        // 一直循环执行到进度条等于大于100
    }
}
```

### 开关(ToggleButton)

### 开关(Switch)

### 数值范围(SeekBar)

### 评分(RatingBar)

### 下拉菜单(Spinner)

### 网页内容(WebView)

**布局文件**

```xml
<WebView  />
```

**页面内容**

```kotlin
// 加载网站
WebView.loadUrl("http://192.168.0.113:8000/robot/")

// 加载文件
WebView.loadUrl("file:///android_asset/myhtml0.html")
```

**设置**
```kotlin
val settings = webView.settings

// 启用JavaScript
settings.javaScriptEnabled = true

// 启用自动加载图片
settings.loadsImagesAutomatically = true

// 启用localStorage
settings.domStorageEnabled = true

// 启用读取文件缓存(manifest生效)
settings.allowFileAccess = true

// 启用浏览器插件(例如 Flash 插件)
settings.pluginState = WebSettings.PluginState.ON

// 加速WebView加载的方法
settings.javaScriptCanOpenWindowsAutomatically = true

// 提高渲染的优先级
settings.setRenderPriority(WebSettings.RenderPriority.HIGH)

// 滚动条风格，为0就是不给滚动条留空间，滚动条覆盖在网页上
webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
```

**client**

```kotlin
class MyWebViewClient : WebViewClient() {
    // 在加载新的网页时被调用，允许开发者拦截网页请求并自行处理
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        val myUrl = request.url.toString()

        // 如果加载的是http或者https就返回false在本软件进行打开
        // 如果加装的是自定义协议链接就返回true交给系统浏览器处理
        if (myUrl.startsWith("http://") || myUrl.startsWith("https://") || myUrl.startsWith("www")) {
            view.loadUrl(myUrl)
            return false
        } else {
            Toast.makeText(view.context, myUrl, Toast.LENGTH_SHORT).show()
            Toast.makeText(view.context, "该链接可能需要跳转到相关应用，以进行拦截", Toast.LENGTH_SHORT).show()
        }

        // 返回 true 表示拦截请求，不加载网页
        // 返回 false 表示继续加载网页
        return true
    }

    // 当页面开始加载时被调用
    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    // 当页面加载完成时被调用
    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)
        // MyWebView.loadUrl("Javascript:alert('hello world')")
        // MyWebView.evaluateJavascript("")
        // MyWebView.evaluateJavascript("Javascript:alert('hello world')",null)
        // MyWebView.addJavascriptInterface()
    }

    // 当页面加载发生错误时被调用
    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
    }
}

class MyWebChromeClient : WebChromeClient() {
        // 当网页需要显示一个 JavaScript alert 对话框时被调用
        override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
            // 返回 true 表示已处理该对话框；返回 false 表示继续使用 WebView 默认的处理逻辑
            return false
        }

        // 当页面加载进度发生变化时被调用
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
        }

        // 当网页标题发生变化时被调用
        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            (view.context as? Activity)?.title = title
            (view.context as? Activity)?.title = view.url
        }
    }

webView.webViewClient = MyWebViewClient()

webView.webChromeClient = MyWebChromeClient()
```

### 自动完成文本视图(AutoCompleteTextView)

提供输入自动补全的功能。

### 多重自动完成文本视图(MultiAutoCompleteTextView)

可输入多个自动补全的关键字。

### 卡片视图(CardView)
常用于显示具有卡片样式的内容。

### 日期选择器(DatePicker)

### 时间选择器(TimePicker)

### 日历视图(CalendarView)

### 数字选择器(NumberPicker)

### 搜索视图(SearchView)

用于输入搜索关键字并执行搜索操作。

## material

### 导航视图(NavigationView)
通常与DrawerLayout配合使用，用于创建导航菜单。

### 选项卡布局(TabLayout)
用于在页面之间切换或分类显示内容。

### 底部导航栏(BottomNavigationView)
用于在应用的底部切换主要导航项。

## 自定义View
...

## 自定义ViewGroup
...

## 自定义样式

### shape(形状)

用于定义视图的背景形状的样式。

通过 shape 标签可以定义矩形、圆角矩形、椭圆、线条等形状，并设置填充颜色、边框、渐变等属性

shape 样式通常作为背景设置，可以应用于大多数具有背景的视图，如按钮、文本框、布局等。

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">

    <!-- 颜色 -->
    <solir android:color="@color/White"/>

    <!-- 圆角半径 radius = 上下左右四个角 -->
    <corners android:radius="30dp"/>

    <!-- 内边距 Padding -->

    <!-- 边框 Stroke-->
    <stroke
        android:width="1dp"
        android:color="@color/White"/>

</shape>
```

### selector(选择器)

用于根据视图状态选择不同样式的样式。

通过 selector 标签可以定义根据视图的状态（如按下、选中、禁用等）显示不同的背景、文本颜色、边框等属性。

selector 样式通常用于按钮、列表项等需要根据状态显示不同样式的视图。

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- android:drawable -->
    <item android:state_checked="true">
        <shape>
            <solid android:color="#AA6600"/>
            <corners android:radius="5dp"/>
        </shape>
    </item>
    <item android:state_checked="false">
        <shape>
            <stroke
                android:width="1dp"
                android:color="#AA6600"/>
            <corners android:radius="5dp"/>
        </shape>
    </item>
</selector>
```

### Gradient(渐变)

用于创建渐变效果，可以在视图的背景或文本颜色中应用渐变。

### Layer List(图层列表)

用于创建多个图层叠加的效果，每个图层可以有不同的背景、边框、形状等。

### Nine Patch(九宫格)

用于定义可缩放的背景图像，可以根据视图的大小自动调整图像的拉伸和填充。

### State List Animator(状态列表动画)

用于定义根据视图状态显示动画效果的样式，比如渐变、缩放、旋转等。

### 使用

```xml
<LinearLayout>
    <Component android:background="@drawable/my_shape" />
    <Component android:background="@drawable/my_selector" />
</LinearLayout>
```

## AlertDialog

Android提供的常用对话框类型，用于向用户展示信息并接收简单的用户反馈。

它可以包含标题、消息文本、按钮等元素。

**Button(Positive，Neutral，Negative)**

```kotlin
val builder = AlertDialog.Builder(this@MyActivityClass)
builder
    .setTitle("编程语言调查")
    .setMessage("你最喜欢哪个编程")
    .setIcon(R.drawable.ic_heart)

builder.setPositiveButton("Python") { dialog, which ->
    Log.i("2042136767", "onCreate: $dialog, $which")
}.setNeutralButton("Java") { dialog, which ->
    Log.i("2042136767", "onCreate: $dialog, $which")
}.setNegativeButton("C++") { dialog, which ->
    Log.i("2042136767", "onCreate: $dialog, $which")
}.show()
```

**Items**

```kotlin
val array = arrayListOf<String>("男","女","外星人")
val builder = AlertDialog.Builder(this@MyActivityClass)

builder.setTitle("选择性别");
builder.setItems(array) {
    override fun onClick(dialog: DialogInterface, which: Int) {
        Log.i("2042136767", "onCreate: 您选择的是: $array[which]")
    }
}.show()
```

**SingleChoiceItems**

```kotlin
val array = arrayListOf<String>("Python","C++","Java","Go","C","易语言")
val builder = AlertDialog.Builder(this@MyActivityClass)
builder.setTitle("选择编程语言")
builder.setSingleChoiceItems(array, 0) {
    override fun onClick(dialog: DialogInterface, which: Int){
        Log.i("2042136767", "onCreate: 您选择的是: $array[which]")
        dialog.dismiss(); // 选择完后,对话框消失
    }
}
builder.setCancelable(false) //点对话框以外的空白处,对话框不会消失
builder.show()
```

**MultiChoiceItems**

```kotlin
val array = arrayListOf<String>("Python","C++","Java","Go","C","易语言")
val isSelected = arrayListOf<Boolean>(true,false,true,false,false,true)

val builder = AlertDialog.Builder(this@MyActivityClass)
builder.setTitle("选择编程语言");
builder.setMultiChoiceItems(array4, isSelected) {
    override fun onClick(dialog: DialogInterface, which: Int, isChecked: Boolean) {
        Log.i("2042136767", "onCreate: 您选择的是: $array[which] : $isChecked")
    }
}.setPositiveButton("确定") {
    override fun onClick(dialog: DialogInterface, which: Int) {
        //
    }
}.setNegativeButton("取消") {
    override fun onClick(dialog: DialogInterface, which: Int) {
        //
    }
}
builder.setCancelable(false)
builder.show()
```

**View**

```kotlin
val builder = AlertDialog.Builder(this@MyActivityClass)

val view = LayoutInflater.from(this@MyActivityClass).inflate(R.layout.layout_dialog,null)
val tvUsername = view.findViewById(R.id.MyDialog_UserName)
val tvPassword = view.findViewById(R.id.MyDialog_PassWord)
val btLogin = MyView.findViewById(R.id.MyDialog_Login)

btLogin.setOnClickListener() {
    override fun onClick(v: View) {
        Log.d("2042136767", "tvUsername: ${tvUsername.getText()}")
        Log.d("2042136767", "tvPassword: ${tvPassword.getText()}");

        // 选择完后,对话框消失
    }
}

builder.setTitle("登录");
builder.setView(view);
builder.setCancelable(false);
builder.show();
```

## ProgressDialog

特殊的对话框，用于在后台进行任务时向用户显示进度。

它通常包含一个进度条和文字提示，用于表示任务的进行情况。

### 案例1

```kotlin
val progressDialog = ProgressDialog(this@MyActivityClass)
progressDialog.setTitle("提示窗口");
progressDialog.setMessage("正在加载");
progressDialog.setOnCancelListener() {
    override fun onCancel(dialog: DialogInterface) {
        ToastUtil.ShowMessagePro(MyAssembly.this,"cancel...",0);
    }
}
progressDialog.setCancelable(false); // 是否可以被取消
progressDialog.show();
/*MyProgressDialog.dismiss(); 使用这个后,不会触发上面的点击事件*/
```

### 案例2

```kotlin
val progressDialog = ProgressDialog(this@MyActivityClass)
progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
progressDialog.setTitle("提示窗口");
progressDialog.setMessage("正在下载...");
progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "取消") {
    override fun onClick(dialog: DialogInterface, which: Int) {
        // 
    }
}
progressDialog.show();
```

### 案例3

```xml
<LinearLayout>
    <ProgressBar
    style="@android:style/Widget.ProgressBar.Horizontal"
    android:max="100"
    android:progress="10" />
    <ProgressBar
    style="@android:style/Widget.Material.ProgressBar.Horizontal"
    android:max="100"
    android:progress="10"
    android:secondaryProgress="20" />
</LinearLayout>
```

## DatePickerDialog&&TimePickerDialog

这两种对话框用于分别选择日期和时间。

它们提供了一个用户友好的界面，让用户可以选择特定的日期和时间。

## Custom Dialog

自定义对话框可以根据应用程序的需求进行布局和设计，并与应用程序的逻辑进行交互。

继承 Dialog, 实现自定义Dialog

**dialog**

```java
public class CustomDialog extends Dialog implements View.OnClickListener{
    private TextView MyTvTitle,MyTvMessage,MyTvCancel,MyTvConfirm;
    private String title,message,cancel,confirm;
    private IOnCancelListener cancelListener;
    private IOnConfirmListener confirmListener;

    public void setTitle(String title) {
        this.title = title;
    }
    public CustomDialog setMessage(String message) {
        this.message = message;
        return this;
    }
    public CustomDialog setCancel(String cancel,IOnCancelListener listener) {
        this.cancel = cancel;
        this.cancelListener = listener;
        return this;
    }
    public CustomDialog setConfirm(String confirm, IOnConfirmListener listener) {
        this.confirm = confirm;
        this.confirmListener = listener;
        return this;
    }

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeId) {
        super(context,themeId);
    }

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.layout_custom_dialog);

        // 宽度
        WindowManager M = getWindow().getWindowManager();
        Display D = M.getDefaultDisplay();
        WindowManager.LayoutParams P =getWindow().getAttributes();
        Point size = new Point();
        D.getSize(size);
        P.width = (int)(size.x * 0.8); //设置dialog的宽度为当前手机屏幕的宽度*0.8(80%)
        getWindow().setAttributes(P);


        MyTvTitle = findViewById(R.id.tv_title);
        MyTvMessage = findViewById(R.id.tv_message);
        MyTvCancel = findViewById(R.id.tv_cancel);
        MyTvConfirm = findViewById(R.id.tv_confirm);

        if(!TextUtils.isEmpty(title)){
            MyTvTitle.setText(title);
        }
        if(!TextUtils.isEmpty(message)){
            MyTvMessage.setText(message);
        }
        if(!TextUtils.isEmpty(cancel)){
            MyTvCancel.setText(cancel);
        }
        if(!TextUtils.isEmpty(confirm)){
            MyTvConfirm.setText(confirm);
        }
        MyTvCancel.setOnClickListener(this);
        MyTvConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel:
                if(cancelListener != null){
                    cancelListener.onCancel(this);
                }
                dismiss();
                break;
            case R.id.tv_confirm:
                if(confirmListener != null){
                    confirmListener.onConfirm(this);
                }
                dismiss();
                break;
        }
    }

    public interface IOnCancelListener{
        void onCancel(CustomDialog dialog);
    }

    public interface IOnConfirmListener{
        void onConfirm(CustomDialog dialog);
    }

}
```

**style**

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="CustomDialog" parent="Theme.AppCompat.Dialog">
        <item name="windowNoTitle">true</item>
        <item name="android:windowIsFloating">true</item>
        <item name="android:windowBackground">@color/black</item>
    </style>
</resources>
```

**xml**

```kotlin
val button = findViewById(R.id.MyAssembly_Dialog);
button.setOnClickListener() {
    override fun onClick(v: View) {
        val customDialog = CustomDialog(this, R.style.CustomDialog)
        customDialog.setTitle("窗口提示")
        customDialog.setMessage("是否删除")
        customDialog.setCancel("取消") {
            override fun onCancel(dialog: CustomDialog) {
                // dialog.cancel()
                ToastUtil.ShowMessage(this,"CanCle")
            }
        }
        customDialog.setConfirm("确认") {
            override fun onConfirm(dialog: CustomDialog) {
                // dialog.cancel()
                ToastUtil.ShowMessage(this,"ConFirm")
            }
        }
        customDialog.show()
    }
}
```

## Toast

```kotlin
// 长短
val toast = Toast.makeText(this, "hello world", Toast.LENGTH_SHORT)
val toast = Toast.makeText(this, "hello world", Toast.LENGTH_LONG)
toast.show()

// 位置
val position = 0
toast.setGravity(Gravity.CENTER, position, position)
toast.show()

// 自定义
val inflater = LayoutInflater.from(activity.this)
val view = inflater.inflate(R.layout.layout_toast, null)
val imageView = view.findViewById(R.id.iv_toast)
val textView = view.findViewById(R.id.tv_toast)
imageView.setImageResource(R.drawable.bid_14215405)
textView.setText("hello world")
toast.setView(view)
toast.show()
```

# 服务(Service&&IntentService)

## 生命周期
```text
onCreate()          当Service第一次创建时调用。在该方法中，通常进行一些初始化操作，如初始化变量、获取系统服务等。
onStartCommand()    当通过调用startService()方法启动Service时调用。在该方法中，Service可以执行后台任务，如网络请求、音乐播放等。它还可以返回一个整数值，用于定义Service的启动类型和行为。
onBind()            当通过调用bindService()方法绑定到Service时调用。在该方法中，Service返回一个IBinder接口的实现，用于与调用者之间进行通信。该方法通常用于实现客户端-服务器模式的通信。
onUnbind()          当通过调用unbindService()方法解绑Service时调用。在该方法中，Service可以执行一些清理操作，如取消注册的监听器、释放资源等。
onDestroy()         当Service即将被销毁时调用。在该方法中，Service应该释放所有的资源，并停止后台任务的执行。
```

## 结构

0. Context (android.content)
0. ContextWrapper (android.content)
0. Service (android.app)

```text
┌ Service (android.app)
├───┼ QuickAccessWalletService (android.service.quickaccesswallet)
│   ├ MediaBrowserService (android.service.media)
│   ├ CarrierMessagingService (android.service.carrier)
│   ├ CameraPrewarmService (android.service.media)
│   ├ TextToSpeechService (android.speech.tts)
│   ├ ConditionProviderService (android.service.notification)
│   ├ PrintService (android.printservice)
│   ├ MediaSession2Service (android.media)
│   ├ IntentService (android.app)
│   ├ TileService (android.service.quicksettings)
│   ├ VpnService (android.net)
│   ├ RemoteViewsService (android.widget)
│   ├ InCallService (android.telecom)
│   ├ HostNfcFService (android.nfc.cardemulation)
│   ├ JobIntentService (androidx.core.app)
│   ├ JobIntentService (androidx.core.app)
│   ├ VoiceInteractionSessionService (android.service.voice)
│   ├ MediaRoute2ProviderService (android.media)
│   ├ DreamService (android.service.dreams)
│   ├ MidiDeviceService (android.media.midi)
│   ├ AbstractInputMethodService (android.inputmethodservice)
│   ├ InputMethodService (android.inputmethodservice)
│   ├ HostApduService (android.nfc.cardemulation)
│   ├ RecognitionService (android.speech)
│   ├ VrListenerService (android.service.vr)
│   ├ VisualVoicemailService (android.telephony)
│   ├ SettingInjectorService (android.location)
│   ├ WallpaperService (android.service.wallpaper)
│   ├ VoiceInteractionService (android.service.voice)
│   ├ ChooserTargetService (android.service.chooser)
│   ├ SpellCheckerService (android.service.textservice)
│   ├ NotificationListenerService (android.service.notification)
│   ├ CallRedirectionService (android.telecom)
│   ├ JobService (android.app.job)
│   ├ OffHostApduService (android.nfc.cardemulation)
│   ├ AccessibilityService (android.accessibilityservice)
│   ├ CarrierMessagingClientService (android.service.carrier)
│   ├ ControlsProviderService (android.service.controls)
│   ├ NotificationCompatSideChannelService (androidx.core.app)
│   ├ NotificationCompatSideChannelService (androidx.core.app)
│   ├ CarrierService (android.service.carrier)
│   ├ AutofillService (android.service.autofill)
│   ├ TvInputService (android.media.tv)
│   ├ CallScreeningService (android.telecom)
│   ├ DeviceAdminService (android.app.admin)
│   └ ConnectionService (android.telecom)
```

## Service

Service是一个基本的Service类，用于执行在后台运行的操作。

它是一个抽象类，需要通过继承并实现其生命周期方法（如onCreate()、onStartCommand()和onDestroy()）来定义自己的服务行为。

Service可以在后台执行长时间运行的任务，例如播放音乐、运行下载任务等。

## IntentService

IntentService是一个抽象类，继承自Service。

它被设计为处理后台任务，并在任务完成后自动停止。

IntentService在单独的工作线程上逐个处理每个传入的Intent请求，并且在所有请求处理完毕后自动停止。

它适用于处理一系列独立的后台任务，例如下载文件、发送网络请求等。

## ForegroundService

ForegroundService是一个在前台运行的Service，它提供了与用户进行交互的能力，并将自己标记为重要的服务，不易被系统终止。

ForegroundService通常用于执行用户能感知到的任务，例如播放音乐、进行导航等，并通过在状态栏显示通知来提醒用户。

## JobIntentService

JobIntentService是从IntentService派生的一个类，用于在后台执行长时间运行的任务。

与IntentService不同，JobIntentService利用了JobScheduler API，以适应Android 8.0及更高版本的后台执行限制。

JobIntentService在后台执行任务期间可以处理更多类型的系统事件，并且在任务完成后自动停止。

## BoundService

BoundService是一种与客户端组件进行绑定的服务，它允许客户端与服务进行通信并执行交互操作。

BoundService提供了一个IBinder接口的实例，客户端可以通过该接口与服务进行通信。

BoundService通常用于通过客户端-服务接口进行双向通信，例如音乐播放器中的控制与播放。

# 广播(Broadcast)

## BroadcastReceiver

广播接收器基类。

用于接收和处理广播消息，可以在应用程序内或跨应用程序之间传递消息。

通过子类化BroadcastReceiver并实现onReceive()方法，可以定义接收到广播时要执行的逻辑。

## RestrictionsReceiver

限制接收器类。

它是用于接收应用程序限制变化的广播消息的特殊接收器。

当应用程序受到限制（例如家长控制）或限制被解除时，这个接收器将被触发

可以在其onReceive()方法中处理相应的逻辑。

## MessengerReceiver in InstrumentationConnection

AndroidX测试库中的Messenger接收器类

用于与测试仪器之间进行通信和交互。

在测试过程中，它可以接收来自测试仪器的消息，并执行相应的操作。

## DeviceAdminReceiver

设备管理器接收器类。

用于管理设备管理员应用程序的各种事件和操作

例如设备锁定、密码重置、设备擦除等

通过子类化DeviceAdminReceiver并实现相应的回调方法，你可以处理设备管理员应用程序的各种事件。

## AppWidgetProvider

小部件提供程序类

它用于创建和管理应用程序小部件的生命周期和行为

通过子类化AppWidgetProvider并实现相应的回调方法，可以定义小部件的外观、更新逻辑以及与小部件相关的事件处理。

```java
public class MyWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // 在小部件更新时执行的操作
        for (int appWidgetId : appWidgetIds) {
            // 更新小部件的UI
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            views.setTextViewText(R.id.widget_text, "Hello Widget!");

            // 更新小部件
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
```

## MbmsDownloadReceiver

多播和广播多媒体服务（MBMS）下载接收器类

它用于接收MBMS下载过程中的广播消息，例如下载开始、完成或失败的通知

通过注册MbmsDownloadReceiver并实现相应的回调方法，可以处理MBMS下载相关的事件和状态。

## WakefulBroadcastReceiver

AndroidX兼容库中的唤醒广播接收器类。

在过去它用于处理唤醒设备并执行后台操作的广播消息。

然而，从Android 8.0开始，它已经被后台服务（Background Service）所取代，不再推荐使用。

## DelegatedAdminReceiver

委派管理员接收器类。它是设备管理员应用程序中的辅助接收器，用于处理特定的事件和操作

例如设备管理员的激活状态变化、密码重置等

通过子类化DelegatedAdminReceiver并实现相应的回调方法，可以处理委派管理员应用程序的各种事件。

# 内容提供者(ContentProvider)

用于管理应用程序的数据共享和跨应用程序访问。

它提供了一种标准化的接口，允许应用程序在安全的方式下共享和访问数据。

ContentProvider可以用于存储和检索结构化数据，如数据库表中的数据，或者访问非结构化数据，如文件

## 特点
0. 数据共享
    - ContentProvider允许应用程序共享数据给其他应用程序或组件。
    - 通过定义合适的URI和数据访问接口，其他应用程序可以查询和修改ContentProvider中的数据。
0. 数据访问控制
    - ContentProvider提供了对数据的精细访问控制。
    - 你可以根据需要设置权限，以确保只有授权的应用程序能够访问和修改数据。
    - 这样可以保护敏感数据的安全性。
0. 数据存储
    - ContentProvider可以用于存储和管理应用程序的数据。
    - 你可以使用SQLite数据库、文件系统或其他数据存储方式作为ContentProvider的后端，从而提供持久化的数据存储。
0. 数据查询和修改
    - 通过ContentProvider，应用程序可以执行查询、插入、更新和删除等常见的数据操作。
    - 通过使用ContentResolver类，其他应用程序可以访问ContentProvider提供的数据操作方法。
0. 跨进程通信
    - ContentProvider支持跨进程通信，允许应用程序在不同的进程中访问和共享数据。
    - 这对于需要在多个应用程序之间交换数据的情况非常有用。
0. URI和数据匹配
    - 通过定义URI和数据匹配规则，ContentProvider可以根据请求的URI和参数，提供对应的数据。
    - 这样可以实现灵活的数据检索和过滤。
    
## ContentProvider
## SearchRecentSuggestionsProvider
## FileProvider
## FileProvider
## SliceProvider
## DocumentsProvider

# 组件通信(Intent)

**启动指定活动(显式)**
```kotlin
// 指定Activity活动
val intent = Intent(this, TargetActivity::class.java)
// 设置数据
intent.putExtra("key", "value")
// 启动Activity活动
startActivity(intent)

// 
val intent = Intent()
intent.setClass(this, TargetActivity::class.java)
startActivity(intent)

// 
val intent = Intent()
intent.setClass(this, "com.qing.example.TargetActivity")
startActivity(intent)

// 
val intent = Intent()
intent.setComponent(ComponentName(this, "com.qing.example.TargetActivity"))
startActivity(intent)
```

**启动指定活动(隐式)**
```kotlin
// 指定Activity活动
val intent = Intent(Intent.ACTION_VIEW)
intent.data = Uri.parse("http://www.example.com")
// 启动Activity活动
startActivity(intent)

// 
val intent = Intent()
intent.setAction("com.qing.example.TargetActivity")
startActivity(intent)
```

**启动后台服务**
```kotlin
// 指定Service后台服务
val intent = Intent(this, MyService::class.java)
// 设置数据
intent.putExtra("key", "value")
// 启动Service后台服务
startService(intent)
```

**发送广播**
```kotlin
// 指定广播
val intent = Intent("com.example.MY_ACTION")
// 设置数据
intent.putExtra("key", "value")
// 发送广播
sendBroadcast(intent)
```

**启动外部应用**
```kotlin
val intent = packageManager.getLaunchIntentForPackage("com.example.targetapp")
startActivity(intent)
```

**启动活动(等待结果)**
```kotlin
val intent = Intent(this, TargetActivity::class.java)
intent.putExtra("key", "value")
startActivityForResult(intent, requestCode)
```

**启动系统应用**
```kotlin
val intent = Intent(Intent.ACTION_SEND)
intent.type = "text/plain"
intent.putExtra(Intent.EXTRA_TEXT, "Hello, world!")
startActivity(intent)
```

**数据: putExtra**
```kotlin
// 设置数据
val intent = Intent(this, TargetActivity::class.java)
intent.putExtra("key", value)
startActivity(intent)

// 获取数据
val receivedValue = intent.getStringExtra("key")
```

**数据: Bundle**
```kotlin
// 设置数据
val intent = Intent(this, TargetActivity::class.java)
val bundle = Bundle()
bundle.putString("key", value)
intent.putExtras(bundle)
startActivity(intent)

// 获取数据
val receivedBundle = intent.extras
val receivedValue = receivedBundle?.getString("key")
```

**数据: Parcelable**
```kotlin
// 设置数据
val intent = Intent(this, TargetActivity::class.java)
intent.putExtra("key", customObject)
startActivity(intent)

// 获取数据
val receivedObject = intent.getParcelableExtra<CustomObject>("key")
```

# 日志(Log)

```text
Log.v(String tag, String msg) 输出详细的日志消息，用于调试和追踪应用程序的细节。
Log.d(String tag, String msg) 输出调试日志消息，用于输出调试信息和运行时状态。
Log.i(String tag, String msg) 输出一般信息的日志消息，用于记录应用程序的正常运行状态。
Log.w(String tag, String msg) 输出警告信息的日志消息，用于标记潜在的问题或异常情况。
Log.e(String tag, String msg) 输出错误信息的日志消息，用于记录严重的错误和异常情况。
```

# 资源管理(Resource Management)

**资源适配**

```text
Android支持根据设备的屏幕密度和方向来适配资源。

例如，可以在不同的drawable文件夹中存储适应不同屏幕密度的图像资源。

假设我们有一个图像资源logo.png，我们可以将它放置在以下文件夹中：

- drawable-mdpi   // 适应中等密度屏幕
- drawable-hdpi   // 适应高密度屏幕
- drawable-xhdpi  // 适应超高密度屏幕
```

**资源国际化**

```text
Android支持应用程序的国际化和本地化。

我们可以创建不同的字符串资源文件来支持不同的语言和地区。

例如，我们可以在strings.xml文件中提供英语字符串，而在strings-es.xml文件中提供西班牙语字符串。
```

**资源压缩**

```text
Android提供了工具来压缩和优化资源文件。

例如，可以使用Android Studio的资源压缩工具来自动去除未使用的资源、压缩图像等，以减小应用程序的大小并提高性能。
```

**layout**

```xml
<!-- activity_main.xml -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/hello_world" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/button_text" />

</LinearLayout>
```

**strings**

```xml
<resources>
    <string name="hello_world">Hello, World!</string>
    <string name="button_text">Click Me</string>
</resources>
```

**Activity**

```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        // R类是由Android系统自动生成的，它包含了应用程序中所有资源的引用，例如R.layout、R.id、R.string等
        // 这里可以使用R类来引用布局文件中的资源ID
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        // 在这里可以对TextView和Button进行操作
    }
}
```

## mipmap
```text
mdpi    48x48 px    
hdpi    72x72 px
xhdpi   96x96 px
xxhdpi  144x144 px
xxxhdpi 192x192 px
```

# 存储(Storage)

## 内部存储（Internal Storage）

每个Android设备都有一个内部存储空间，用于存储应用的私有数据。

这部分存储空间对应用来说是私有和安全的，其他应用无法访问。

开发者可以使用内部存储来存储应用的数据库、设置文件和缓存等数据。

## 外部存储（External Storage）

Android设备通常具有可插拔的外部存储介质，如SD卡。

外部存储可用于存储应用的公共数据，如照片、音频、视频等。外部存储通常是共享的，其他应用和用户可以读取和写入其中的数据。

开发者可以使用Android的API来访问外部存储，并在适当的权限下读取和写入数据。

## SharedPreferences

SharedPreferences是一种轻量级的键值对存储机制，用于存储应用的简单配置信息和用户偏好设置。

SharedPreferences存储的数据以XML文件的形式保存在设备的内部存储中。

## 数据库（Database）

Android提供了SQLite数据库作为一种内置的关系型数据库解决方案。

开发者可以使用SQLite来创建和管理应用的结构化数据，如用户信息、日志等。

SQLite数据库可以存储在内部存储或外部存储上

# 网络通信(Networking)

NetworkInfo、ConnectivityManager

## HttpURLConnection

Android提供了HttpURLConnection类，用于进行基于HTTP协议的网络通信。它提供了一组方法来发送HTTP请求、处理响应和管理连接。开发者可以使用HttpURLConnection来与服务器进行数据交换，如发送GET和POST请求，上传和下载文件等。

## OkHttp

OkHttp是一个流行的开源HTTP客户端库，提供了简单而强大的API，用于进行HTTP请求和响应的处理。它支持同步和异步请求、请求拦截器、连接池管理、文件上传和下载等功能。OkHttp被广泛用于Android应用的网络通信。

## Volley

Volley是一个用于网络通信的库，由Google开发并提供给Android开发者使用。它提供了简化的API，用于发送网络请求、处理响应和管理请求队列。Volley还具有缓存机制、图片加载和处理等功能，使得网络通信变得更加简单和高效。

## Retrofit

Retrofit是一个基于OkHttp的RESTful网络请求库，用于简化网络请求的定义和处理。它使用注解来定义请求接口，自动进行请求参数的序列化和响应的反序列化。Retrofit支持各种数据格式，如JSON、XML等，并提供了灵活的回调机制和错误处理。

## WebSocket

Android提供了对WebSocket协议的支持，使开发者可以实现实时双向通信。WebSocket提供了一种持久化的连接机制，可以在客户端和服务器之间进行全双工通信。Android的WebSocket API使得开发者可以创建WebSocket连接、发送和接收消息，并处理连接状态变化。

## WebSocket

Android提供了对WebSocket协议的支持，使开发者可以实现实时双向通信。WebSocket提供了一种持久化的连接机制，可以在客户端和服务器之间进行全双工通信。Android的WebSocket API使得开发者可以创建WebSocket连接、发送和接收消息，并处理连接状态变化。

# 学习

0. 组件化(AppModule, ServiceModule, SystemModule, LibCommon)
    - 如果使用组件化开发的话, 需要ARouter这种才能完成模块间的路由、通信、解耦
0. 自定义View(复合, 继承, 自绘)
0. TODO
    - https://juejin.cn/post/7033954652315975688
    - https://juejin.cn/post/6986527303043907592#heading-14

## 一阶段

### 基础

Android组件、布局、UI 设计、数据存储、网络通信

### 刷机

学习 Android 刷机工具的使用，了解刷机过程和原理

### 反编译

掌握 Android APK 的反编译技术，了解 APK 的结构和修改方法。

#### Apktool

Apktool 是一个开源的命令行工具，用于解包和反编译 Android APK 文件。它能够还原 APK 文件的资源文件和部分源代码。你可以使用 Apktool 解析 APK 文件，查看和编辑其内容。Apktool 的开源地址为：https://github.com/iBotPeaches/Apktool

#### jadx

jadx 是一个开源的命令行工具和库，用于将 Android APK 文件反编译为 Java 源代码。它能够将 Dex 文件转换为可读的 Java 代码，并提供了一种简单的方式来查看和浏览反编译后的代码。jadx 的开源地址为：https://github.com/skylot/jadx

#### jadx-gui

jadx-gui 是 jadx 的图形界面版本，提供了一个用户友好的界面来反编译和浏览 APK 文件的源代码。它基于 jadx 库，使得代码浏览更加直观和方便。jadx-gui 的开源地址为：https://github.com/skylot/jadx/tree/master/jadx-gui

### 差分包

学习 Android 差分包制作工具和方法，了解差分包的生成和应用。

### FOTA

了解 Android 的 FOTA（Firmware Over-The-Air）机制，包括固件升级和远程管理

## 二阶段

### linux&&shell&&python
Linux 操作系统，包括文件系统、进程管理、权限管理等。

掌握常用的 Linux 命令和工具，熟悉 Shell 脚本编程和 Python 编程。

### 底层硬件
学习底层硬件原理，包括处理器架构、内存管理、硬件接口等。

了解 Android 系统与底层硬件的交互方式，熟悉底层硬件驱动开发。

### 嵌入式
学习嵌入式系统的基本原理，包括硬件架构、操作系统、设备驱动等。

熟悉嵌入式开发平台和工具链，例如交叉编译工具、调试工具等。

## 三阶段

### ROM
学习 Android ROM 的结构和原理，了解如何进行二次修改和定制。

参与开源 ROM 项目或自行定制 ROM，积累相关经验

学习 ROM 的移植和适配技术，包括处理器平台移植、设备驱动移植等

## 项目

### tool应用
### todo应用
### 天气应用
### 新闻阅读应用
### 社交媒体应用
### 音乐播放器应用

## 开源项目

### cheesesquare
https://github.com/chrisbanes/cheesesquare

0. Collapsing Toolbar
0. FloatingActionButton
0. View anchoring
0. NavigationView
0. Snackbar

### Screenshots
https://github.com/jhansireddy/AndroidScannerDemo

ScanLibrary是一个建立在OpenCV之上的android文档扫描库

该应用程序，能够选择准确的边缘，并从选定的4条边缘相应地裁剪文档，并更改裁剪图像的透视变换。

# 

## tabs

```xml
<?xml version="1.0" encoding="utf-8"?>

<androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/main_content"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.google.android.material.appbar.AppBarLayout
        android:id="@+id/appbar"
        android:fitsSystemWindows="true"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

        <com.google.android.material.tabs.TabLayout
            android:id="@+id/tabs"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

    </com.google.android.material.appbar.AppBarLayout>


    <androidx.viewpager.widget.ViewPager
        android:id="@+id/viewpager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior" />

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

```kotlin

class CodeScan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.code_scan)

        // ...
        val viewPager: ViewPager = findViewById(R.id.viewpager)
        val tabLayout: TabLayout = findViewById(R.id.tabs)
        viewPager.adapter = Adapter(supportFragmentManager).apply {
            addFragment(Home(), "Category 1")
            addFragment(Profile(), "Category 2")
            addFragment(Recommend(), "Category 3")
        }
        tabLayout.setupWithViewPager(viewPager)
    }

    internal class Adapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val fragments: MutableList<Fragment> = ArrayList()
        private val titles: MutableList<String> = ArrayList()

        fun addFragment(fragment: Fragment, title: String) {
            fragments.add(fragment)
            titles.add(title)
        }

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size

        override fun getPageTitle(position: Int): CharSequence = titles[position]
    }

}

```

## 下滑

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/main_content"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true">

    <com.google.android.material.appbar.AppBarLayout
        android:id="@+id/appbar"
        android:layout_width="match_parent"
        android:layout_height="256dp"
        android:fitsSystemWindows="true"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

        <com.google.android.material.appbar.CollapsingToolbarLayout
            android:id="@+id/collapsing_toolbar"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:fitsSystemWindows="true"
            app:contentScrim="?attr/colorPrimary"
            app:expandedTitleMarginEnd="64dp"
            app:expandedTitleMarginStart="48dp"
            app:layout_scrollFlags="scroll|exitUntilCollapsed">

            <ImageView
                android:id="@+id/backdrop"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:fitsSystemWindows="true"
                android:scaleType="centerCrop"
                app:layout_collapseMode="parallax" />

            <androidx.appcompat.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                app:layout_collapseMode="pin"
                app:popupTheme="@style/ThemeOverlay.AppCompat.Light" />

        </com.google.android.material.appbar.CollapsingToolbarLayout>

    </com.google.android.material.appbar.AppBarLayout>

    <androidx.core.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"
            android:paddingTop="24dp">

            <com.google.android.material.card.MaterialCardView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="16dp">

                <LinearLayout
                    style="@style/Widget.CardContent"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content">

                    <TextView
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="Info"
                        android:textAppearance="@style/TextAppearance.AppCompat.Title" />

                    <TextView
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="@string/cheese_ipsum" />


                </LinearLayout>

            </com.google.android.material.card.MaterialCardView>

            <com.google.android.material.card.MaterialCardView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginLeft="16dp"
                android:layout_marginRight="16dp"
                android:layout_marginBottom="16dp">

                <LinearLayout
                    style="@style/Widget.CardContent"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content">

                    <TextView
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="Friends"
                        android:textAppearance="@style/TextAppearance.AppCompat.Title" />

                    <TextView
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="@string/cheese_ipsum" />

                </LinearLayout>

            </com.google.android.material.card.MaterialCardView>

            <com.google.android.material.card.MaterialCardView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginLeft="16dp"
                android:layout_marginRight="16dp"
                android:layout_marginBottom="16dp">

                <LinearLayout
                    style="@style/Widget.CardContent"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content">

                    <TextView
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="Related"
                        android:textAppearance="@style/TextAppearance.AppCompat.Title" />

                    <TextView
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="@string/cheese_ipsum" />

                </LinearLayout>

            </com.google.android.material.card.MaterialCardView>

        </LinearLayout>

    </androidx.core.widget.NestedScrollView>

    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="16dp"
        android:clickable="true"
        android:focusable="true"
        app:layout_anchor="@id/appbar"
        app:layout_anchorGravity="bottom|right|end"
        app:srcCompat="@drawable/ic_menu" />

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

```kotlin
package com.qing.rainfall_tool.ui.functions

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.qing.rainfall_tool.R

class RMap : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_map)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // 标题
        val collapsingToolbar: CollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
        collapsingToolbar.title = intent.getStringExtra(EXTRA_NAME)

        // 背景图
        loadBackdrop()
    }
    private fun loadBackdrop() {
        val imageView: ImageView = findViewById(R.id.backdrop)
        Glide.with(imageView)
            .load(R.drawable.title)
            .apply(RequestOptions.centerCropTransform())
            .into(imageView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.sample_actions, menu)
        return true
    }

    companion object {
        const val EXTRA_NAME = "cheese_name"
    }
}
```

## 
```java

// TODO: 显示器
/*
环境光                 29.00000 lux
自动亮度调节            [手动,自动]
自动息屏               [2分钟,5分钟,10分钟]
分辨率                 1080 x 2160px / 393 x 785 dp
最小宽度                392dp
状态栏高度              76px / 28dp
显示器密度               2.75
字体密度                2.75
PPI                     403
屏幕密度                440dp / xxhdpi / 2.75x
精确密度                403.411 x 403.411 dp
屏幕尺寸                2.7" x 5.4" / 6.0英寸
刷新率                  60.0fps
 */

// TODO: 网络
/*
类型      [WiFi,移动网络]
ssid     ...
bssid    00:00:00:00:00:00
隐藏ssid  [true, false]
连接速度    866Mbps
频率       5745MHz
代理      [无代理, ...]
网关      192.168.0.1
mask     255.255.255.0
DNS1     192.168.1.1
DNS2     192.168.0.1
ipv4     192.168.0.113
ipv6     FE80::A650:46FF:FE1E:253
Mac      A4:50:46:1E:02:53
公网IP    223.73.34.72
 */

// TODO: 蓝牙
/*
蓝牙名称        ...
是否启用        [true,false]
 */

// TODO: NFC
/*
是否启用        [true,false]
 */

// TODO: 红外
/*
是否启用        [true,false]
 */

// TODO: 系统
/*
android版本       10 / Q / API 29
SDK是否为预览版    [false, true]
系统类型           user
MIUI             V125 / V12.5.1.0.QDGCNXM
版本号            QKQ1.190828.002
内核版本          4.9.186-perf-g10af704
基带版本          ...
android安全补丁   2020-12-01
ROM              MIUI
ROM版本          12.5.1.0
构建时间          2021-07-07 12:52:51
HttpUserAgent   Dalvik/2.1.0 (Linux; U; Android  10; MIX  2S  MIUI /V12.5.1.0.QDGCNXM)
host            c3-miui-ota-bd133.bj
开机时间          2023-10-17 19:51:44
默认locale       zh-cn
时区             中国标准时间(GMT+08:00)
NTP服务器         pool.ntp.org
USB调试           已启用
品牌              xiaomi
制造商            xiaomi
主板              sdm845
型号              MIX 2s
 */

// TODO: 储存
/*
RAM: 总共内存, 空闲内存, 使用内存
内部储存: 总共内存, 空闲内存, 使用内存
 */

// TODO: 芯片
/*
SOC型号             SDM845
SOC供应商           Qualcomm
SOC核心数量         8
SOC操作系统架构      aarch64
SOC-ABI            arm64-v8a
SOC介绍             ...

GPU                Adreno(TM) 630
GPU供应商           Qualcomm
OpenGL ES          CM 1.1
 */

// TODO: ID, SIM, 相机, 传感器, 电池, 虚拟机, 编解码器, drm, 系统属性, sqlite

```


