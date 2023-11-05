package com.qing.rainfall_tool.ui.test

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
import java.util.ArrayList

class TestTab : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_test_tab)

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