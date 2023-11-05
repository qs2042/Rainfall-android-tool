package com.qing.rainfall_tool.ui.functions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.qing.lib_common.NetworkUtils
import com.qing.rainfall_tool.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HostsManager : AppCompatActivity() {
    private lateinit var fab: FloatingActionButton

    private lateinit var recyclerView: RecyclerView

    // 模拟储存在数据库的数据
    companion object {
        val list = arrayListOf(
            Pojo("baidu", "www.baidu.com", "192.168.1.1"),
            Pojo("bbs", "www.bbs.com", "192.168.1.2"),
            Pojo("blbl", "www.blbl.com", "192.168.1.3")
        )
    }

    // other
    class Pojo(val label: String, val host: String, val ip: String)

    class RecycleAdapter(
        private val context: Context,
        private val list: ArrayList<Pojo>
    ) : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
        private var onItemClickListener: OnItemClickListener? = null

        interface OnItemClickListener {
            fun onItemClick(position: Int)
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            /*var host: TextView? = null
            var ip: TextView? = null

            init {
                host = itemView.findViewById<View>(R.id.tv_host) as TextView
                ip = itemView.findViewById<View>(R.id.tv_ip) as TextView
            }*/
            var host: TextView = itemView.findViewById<View>(R.id.tv_host) as TextView
            var ip: TextView = itemView.findViewById<View>(R.id.tv_ip) as TextView
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater =
                LayoutInflater.from(context)
                    .inflate(R.layout.item_hosts_ip, parent, false)
            return ViewHolder(inflater!!)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = list[position]

            holder.host.text = item.host
            holder.ip.text = item.ip

            holder.ip.setOnClickListener {
                onItemClickListener?.onItemClick(position)
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }

        fun setOnItemClickListener(listener: OnItemClickListener?) {
            onItemClickListener = listener
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_hosts_manager)

        // recyclerView
        recyclerView = findViewById(R.id.recycler_view)

        val adapter = RecycleAdapter(this, list)
        adapter.setOnItemClickListener(object : RecycleAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                // 实现点击事件的逻辑
                Toast.makeText(this@HostsManager, "$recyclerView", Toast.LENGTH_SHORT).show()
                showUpdateHostDialog()
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 1)


        // fab
        fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            showAddHostDialog()
        }


    }

    // TODO: 修改存在的hosts
    private fun showUpdateHostDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_host_ip, null)
        builder.setView(dialogView)

        val editTextHost: EditText = dialogView.findViewById(R.id.editTextHost)
        val editTextIP: EditText = dialogView.findViewById(R.id.editTextIP)

        builder.setTitle("修改host")
            .setPositiveButton("修改") { dialog, which ->
                val context = this
                val host = editTextHost.text.toString()
                val ip = editTextIP.text.toString()

                val job = GlobalScope.launch(Dispatchers.IO) {
                    // 在后台执行耗时操作
                    if (!(NetworkUtils.isDomainName(host) || NetworkUtils.isIPAddress(host))) {
                        // host是否为域名或IP
                        runOnUiThread {
                            // 主线程中执行Toast
                            Toast.makeText(this@HostsManager, "添加失败, Host非法格式", Toast.LENGTH_SHORT)
                                .show()
                        }
                        return@launch
                    }
                    if (!NetworkUtils.isIPAddress(ip)) {
                        // IP是否为合法格式
                        runOnUiThread {
                            Toast.makeText(context, "添加失败, IP非法格式", Toast.LENGTH_SHORT).show()
                        }
                        return@launch
                    }


                    // 在主线程中更新 UI 视图
                    withContext(Dispatchers.Main) {
                        // 添加数据
                        list.add(Pojo("null", host, ip))
                        recyclerView.adapter?.notifyDataSetChanged()

                        Toast.makeText(context, "添加成功: ${list.size}", Toast.LENGTH_SHORT).show()
                    }


                }
            }
            .setNeutralButton("删除") { dialog, which -> dialog.dismiss() }
            .setNegativeButton(
                "取消"
            ) { dialog, which -> dialog.dismiss() }

        val dialog = builder.create()
        dialog.show()
    }


    private fun showAddHostDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_host_ip, null)
        builder.setView(dialogView)

        val editTextHost: EditText = dialogView.findViewById(R.id.editTextHost)
        val editTextIP: EditText = dialogView.findViewById(R.id.editTextIP)

        builder.setTitle("添加host")
            .setPositiveButton("添加") { dialog, which ->
                val context = this
                val host = editTextHost.text.toString()
                val ip = editTextIP.text.toString()

                val job = GlobalScope.launch(Dispatchers.IO) {
                    // 在后台执行耗时操作
                    if (!(NetworkUtils.isDomainName(host) || NetworkUtils.isIPAddress(host))) {
                        // host是否为域名或IP
                        runOnUiThread {
                            // 主线程中执行Toast
                            Toast.makeText(this@HostsManager, "添加失败, Host非法格式", Toast.LENGTH_SHORT)
                                .show()
                        }
                        return@launch
                    }
                    if (!NetworkUtils.isIPAddress(ip)) {
                        // IP是否为合法格式
                        runOnUiThread {
                            Toast.makeText(context, "添加失败, IP非法格式", Toast.LENGTH_SHORT).show()
                        }
                        return@launch
                    }


                    // 在主线程中更新 UI 视图
                    withContext(Dispatchers.Main) {
                        // 添加数据
                        list.add(Pojo("null", host, ip))
                        recyclerView.adapter?.notifyDataSetChanged()

                        Toast.makeText(context, "添加成功: ${list.size}", Toast.LENGTH_SHORT).show()
                    }


                }
            }
            .setNegativeButton(
                "取消"
            ) { dialog, which -> dialog.dismiss() }

        val dialog = builder.create()
        dialog.show()
    }
}