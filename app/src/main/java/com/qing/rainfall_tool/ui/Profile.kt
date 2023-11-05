package com.qing.rainfall_tool.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.switchmaterial.SwitchMaterial
import com.qing.rainfall_tool.MainActivity
import com.qing.rainfall_tool.R
import com.qing.rainfall_tool.ui.functions.RMap
import java.util.*

class Profile : Fragment() {
    private lateinit var v: View

    companion object {
        var orientationStatus = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.v = view

        initNightMode()

        initCZMode()

        initRequestedOrientation()

        loadBackdrop()

        /*val toolbar: Toolbar = v.findViewById(R.id.toolbar)
        activity.setSupportActionBar(toolbar)*/
        /*
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            activity.onBackPressed()
        }*/

        // 标题
        val collapsingToolbar: CollapsingToolbarLayout = v.findViewById(R.id.collapsing_toolbar)
//        collapsingToolbar.title = activity?.intent?.getStringExtra(RMap.EXTRA_NAME)
        collapsingToolbar.title = "暂无设置"
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private fun initRequestedOrientation() {
        val switchButton3: SwitchMaterial = v.findViewById(R.id.switchButton3)
        switchButton3.isChecked = false

        switchButton3.setOnCheckedChangeListener { buttonView, isChecked ->
            // 判断检查当前是否已经是横屏模式
            if (isChecked && activity?.requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
            // 判断检查当前是否已经是竖屏模式
            else if (!isChecked && activity?.requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }
    }


    // 重新加载当前活动以应用新的语言设置
    private fun reloadActivity(activity: Activity) {
        val intent = activity.intent
        activity.finish()
        MainActivity.currentFragment = null
        activity.startActivity(intent)
    }

    private fun setAppLanguage(s: String) {
        // Tips: 切换语言的话, 需要重新加载布局, 可以试试recreate()函数
        val locale = Locale(s)
        val resources = resources
        val configuration = resources.configuration
        configuration.setLocale(locale)// Locale.ENGLISH
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    private fun initCZMode() {
        var currentLocale = resources.configuration.locale

        val switchButton2: SwitchMaterial = v.findViewById(R.id.switchButton2)
        switchButton2.isChecked = currentLocale.language == "zh"

        switchButton2.setOnCheckedChangeListener { buttonView, isChecked ->
            currentLocale = resources.configuration.locale
            if (currentLocale.language == "zh") {
                setAppLanguage("en")
            } else {
                setAppLanguage("zh")
            }
            this@Profile.activity?.let { reloadActivity(it) }
        }
    }

    private fun initNightMode() {
        val mode = AppCompatDelegate.getDefaultNightMode()
        val switchButton: SwitchMaterial = v.findViewById(R.id.switchButton)
        switchButton.isChecked = mode == 2
        switchButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun loadBackdrop() {
        val imageView: ImageView = v.findViewById(R.id.backdrop)
        Glide.with(imageView)
            .load(R.drawable.title)
            .apply(RequestOptions.centerCropTransform())
            .into(imageView)
    }
}