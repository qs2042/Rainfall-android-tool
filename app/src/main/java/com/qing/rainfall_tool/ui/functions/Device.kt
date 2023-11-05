package com.qing.rainfall_tool.ui.functions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.qing.rainfall_tool.R
import com.qing.rainfall_tool.ui.Home
import com.qing.rainfall_tool.ui.Profile
import com.qing.rainfall_tool.ui.Recommend
import com.qing.rainfall_tool.ui.test.TestTab
import java.util.ArrayList

class Device : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_device)

        // ...
        val viewPager: ViewPager = findViewById(R.id.viewpager)
        val tabLayout: TabLayout = findViewById(R.id.tabs)
        viewPager.adapter = Adapter(supportFragmentManager).apply {
            addFragment(Home(), "屏幕")
            addFragment(Profile(), "网络")
            addFragment(Recommend(), "蓝牙")
            addFragment(Recommend(), "NFC")
            addFragment(Recommend(), "红外")
            addFragment(Recommend(), "系统")
            addFragment(Recommend(), "储存")
            addFragment(Recommend(), "芯片")
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