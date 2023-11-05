package com.qing.rainfall_tool

import android.annotation.SuppressLint
import android.content.*
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.qing.rainfall_tool.ui.*

class MainActivity : AppCompatActivity() {
    // fragment
    private val fragmentManager: FragmentManager = supportFragmentManager
    private var fragmentTransaction: FragmentTransaction? = null

    // 滑动布局
    private lateinit var drawerLayout: DrawerLayout

    // 工具栏
    private lateinit var toolbar: Toolbar

    //
    private lateinit var navigationView: NavigationView

    //
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var countReceiver: BroadcastReceiver

    companion object {
        var currentFragment: Fragment? = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 初始化
        init()

        // 初始化toolbar
        initToolbar()

        // 初始化侧边菜单
        initNavigationView()

        // 初始化底部导航栏
        initBottomNavigationView()

        // 初始化Fragment
        initFragment()

        // 服务
//        startService()

        // 广播
//        startReceiver()

        // 内容提供者
//        testContentProvider()

    }

    // 内容提供者
    private fun testContentProvider() {
        // 不需要注册ContentProvider, android会自动根据url来选择
        // 它会先根据authority查找, 也就是AndroidManifest中的authorities(com.qing.rainfall_tool.provider.one)
        // 如果有那就加载类, 那就根据AndroidManifest中的name(com.qing.rainfall_tool.content_provider.MyContentProvider)
        // /后面的也就是path, 需要你自己在ContentProvider中进行判断
        // 如果是user, 那就xxx, 如果是user/#, 那就xxx
        // 如果是book, 那就xxx, 如果是book/#, 那就xxx
        val url = "content://com.qing.rainfall_tool.provider.one/user"

        // 添加数据
        val uriAdd = Uri.parse("$url/#")
        val values = ContentValues().apply {
            put("user_name", "张三")
            put("user_age", 18)
            put("user_sex", 0)
        }
        val newUri = contentResolver.insert(uriAdd, values)
        val newId = newUri?.pathSegments?.get(1)
        Log.i("2042", "testContentProvider: 请求 : uriAdd($uriAdd) : values($values)")
        Log.i("2042", "testContentProvider: 结果 : newUri($newUri) : newId($newId)")

        // 查询数据
        val uriSelect = Uri.parse(url)
        val cursor = contentResolver.query(uriSelect, null, null, null, null)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") val id = cursor.getInt(cursor.getColumnIndex("id"))
                @SuppressLint("Range") val name =
                    cursor.getString(cursor.getColumnIndex("user_name"))
                @SuppressLint("Range") val age = cursor.getInt(cursor.getColumnIndex("user_age"))
                @SuppressLint("Range") val sex = cursor.getInt(cursor.getColumnIndex("user_sex"))
                Log.i("2042", "id($id), name($name), age($age), sex($sex)")
            }
            cursor.close();
        }

        // 更新数据
        val uriUpdate = Uri.parse("$url/1")
        val updateValues = ContentValues()
        updateValues.put("user_age", 81)
        val cursor2 = contentResolver.update(uriUpdate, updateValues, null, null);
        Log.i("2042", "testContentProvider: 更新结果 : $cursor2")

        // 删除数据
        val uriDelete = Uri.parse("$url/$newId")
        val cursor3 = contentResolver.delete(uriDelete, null, null);
        Log.i("2042", "testContentProvider: 删除结果 : $cursor3")
    }

    // 广播
    private fun startReceiver() {
        // 动态注册receiver
//        val receiver = MyBroadcastReceiver()
//        val filter = IntentFilter("com.qing.rainfall_tool.MY_ACTION")
//        registerReceiver(receiver, filter)

        // 然后再向receiver发送
        val intent = Intent("com.qing.rainfall_tool.MY_ACTION")
        sendBroadcast(intent)
    }

    // 服务
    private fun startService() {
        // 注册BroadcastReceiver
        countReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == "COUNT_UPDATED") {
                    val count = intent.getIntExtra("count", 0)
                    Log.i("2042136767", "onReceive: $count")
                }
            }
        }

        val filter = IntentFilter("COUNT_UPDATED")
        LocalBroadcastManager.getInstance(this).registerReceiver(countReceiver, filter)

        // 启动Service
        val serviceIntent = Intent(this, MainService::class.java)
        startService(serviceIntent)
    }

    private fun stopService() {
        // 停止Service
        val serviceIntent = Intent(this, MainService::class.java)
        stopService(serviceIntent)
        // 注销BroadcastReceiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(countReceiver)
    }
    /*// 当前应用切后台的时候(例如: 在应用里接电话)
    override fun onPause() {
        super.onPause()
        stopService()
    }
    override fun onRestart() {
        super.onRestart()
        startService()
    }
    override fun onDestroy() {
        super.onDestroy()
        stopService()
    }*/

    // 选项菜单: 加载布局, 配置菜单项, 配置菜单项事件
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.sample_actions, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        // 为多选赋值选中状态
        when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> {
                menu.findItem(R.id.menu_night_mode_system).isChecked = true
            }
            AppCompatDelegate.MODE_NIGHT_AUTO_TIME -> {
                menu.findItem(R.id.menu_night_mode_auto).isChecked = true
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                menu.findItem(R.id.menu_night_mode_night).isChecked = true
            }
            AppCompatDelegate.MODE_NIGHT_NO -> {
                menu.findItem(R.id.menu_night_mode_day).isChecked = true
            }
            AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY -> {
                menu.findItem(R.id.menu_night_mode_battery).isChecked = true
            }
            AppCompatDelegate.MODE_NIGHT_UNSPECIFIED -> {
                menu.findItem(R.id.menu_night_mode_unspecified).isChecked = true
            }
        }
        //
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
            // 跟随系统
            R.id.menu_night_mode_system -> {
                setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
            // 日间模式
            R.id.menu_night_mode_day -> {
                setNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            // 夜间模式
            R.id.menu_night_mode_night -> {
                setNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            // 根据系统的时间自动切换
            R.id.menu_night_mode_auto -> {
                setNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_TIME) // MODE_NIGHT_AUTO
            }
            // 根据设备的主题、系统设置
            R.id.menu_night_mode_unspecified -> {
                setNightMode(AppCompatDelegate.MODE_NIGHT_UNSPECIFIED)
            }
            // 根据设备的电池状态
            R.id.menu_night_mode_battery -> {
                setNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            }
            R.id.action_simple -> {
                Toast.makeText(this, "已切换", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setNightMode(@AppCompatDelegate.NightMode nightMode: Int) {
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }

    private fun init() {
        // 全屏模式(但是保存状态栏, 默认的主题才行)
        // requestWindowFeature(Window.FEATURE_NO_TITLE)

        // 设置视图
        setContentView(R.layout.activity_main)

        //
        drawerLayout = findViewById(R.id.activity_main_layout)
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.nav_view)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.let { ab ->
            // 标题&&子标题
            ab.title = "RTool"
//            ab.subtitle = "v1.0"

            // 图标&&Logo
//            ab.setIcon(R.drawable.ic_r)
//            ab.setLogo(android.R.drawable.ic_notification_overlay)

            // 是否显示导航按钮, 导航按钮是否可用, 导航按钮的指示器图标
            ab.setDisplayHomeAsUpEnabled(true)
            ab.setHomeButtonEnabled(false)
            ab.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }

    private fun initNavigationView() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
//            drawerLayout.closeDrawers()
            true
        }
    }

    private fun initBottomNavigationView() {
        // 设置默认选项
        bottomNavigationView.selectedItemId = R.id.navigation_home
        // 设置
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            // TODO: addToBackStack和commit可以剥离出来, 不用重复写那么多行
            val bool = when (menuItem.itemId) {
                R.id.navigation_dashboard -> {
//                    fragmentTransaction.hide(currentFragment!!)
                    currentFragment = Dashboard()
                    true
                }
                R.id.navigation_function -> {
//                    fragmentTransaction.hide(currentFragment!!)
                    currentFragment = Functions()
                    true
                }
                R.id.navigation_home -> {
//                    fragmentTransaction.hide(currentFragment!!)
                    currentFragment = Home()
                    true
                }
                R.id.navigation_recommend -> {
//                    fragmentTransaction.hide(currentFragment!!)
                    currentFragment = Recommend()
                    true
                }
                R.id.navigation_profile -> {
//                    fragmentTransaction.hide(currentFragment!!)
                    currentFragment = Profile()
                    true
                }
                else -> false
            }

            if (bool) {
                fragmentTransaction.replace(R.id.fragment_container, currentFragment!!, "tag")
                fragmentTransaction.addToBackStack("tag")
                fragmentTransaction.commit()
            }

            bool
        }
    }

    private fun initFragment() {
        // 开启一个事务
        if (fragmentTransaction == null) {
            fragmentTransaction = fragmentManager.beginTransaction()
        }

        // TODO: BUG, 处在同一个事物中, 但还是一直为true(也就是空的), 无法判断是否加载了fragment
//        Log.i("2042", "initFragment: ${fragmentTransaction!!.isEmpty}")

        // 如果是第一次加载
        if (currentFragment == null) {
            // 创建并添加Fragment到容器中
            currentFragment = Home()
        }
        fragmentTransaction!!.replace(R.id.fragment_container, currentFragment!!, "tag")

        // 提交事务
        fragmentTransaction!!.commit()
    }
}