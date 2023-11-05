package com.qing.rainfall_tool.ui.functions

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.qing.lib_common.NetworkUtils
import com.qing.rainfall_tool.R
import kotlinx.coroutines.*

// TODO: 筛选列表: 开启or关闭, 常见端口, 指定端口, 指定范围端口
class PortScan : AppCompatActivity() {
    // member
    private var job: Job? = null                    // 任务
    private lateinit var recyclerView: RecyclerView // RecyclerView

    // member(ui)
    private lateinit var textIp: TextInputEditText  // 文本框
    private lateinit var buttonStart: Button        // 按钮
    private lateinit var filterLayout: RadioGroup   // 按钮组

    // member(data)
    private var list: ArrayList<HashMap<String, Any>> = ArrayList()
    private var currentPort: Int = 1
    private lateinit var currentIp: String
    private var status: Boolean = false

    // static
    companion object {
        val portMap = mutableMapOf(
            80 to "HTTP 页面默认的端口",
            443 to "HTTPS",
            22 to "SSH (Secure Shell)",
            21 to "FTP (File Transfer Protocol)",
            25 to "SMTP (Simple Mail Transfer Protocol)",
            110 to "POP3 (Post Office Protocol version 3)",
            143 to "IMAP (Internet Message Access Protocol)",
            53 to "DNS (Domain Name System)",
            3389 to "RDP (Remote Desktop Protocol)",
            3306 to "MySQL Database",
            5432 to "PostgreSQL Database",
            8080 to "HTTP Alternate",
            8443 to "HTTPS Alternate",
            23 to "Telnet",
            69 to "TFTP (Trivial File Transfer Protocol)",
            161 to "SNMP (Simple Network Management Protocol)",
            162 to "SNMP Trap",
            389 to "LDAP (Lightweight Directory Access Protocol)",
            636 to "LDAPS (LDAP over SSL/TLS)",
            465 to "SMTPS (SMTP over SSL/TLS)",
            995 to "POP3S (POP3 over SSL/TLS)",
            993 to "IMAPS (IMAP over SSL/TLS)",
            1433 to "Microsoft SQL Server",
            1521 to "Oracle Database",
            3307 to "MariaDB Database",
            5439 to "Amazon Redshift",
            5672 to "AMQP (Advanced Message Queuing Protocol)",
            27017 to "MongoDB",
            5601 to "Kibana",
            9200 to "Elasticsearch",
            6379 to "Redis",
            8081 to "Proxy Server",
            8888 to "Proxy Server (Alternative)",
            111 to "RPC (Remote Procedure Call)",
            2049 to "NFS (Network File System)",
            5900 to "VNC (Virtual Network Computing)",
            5901 to "VNC Alternate",
            8000 to "HTTP Alternate",
            8880 to "HTTP Alternate",
            3891 to "Oracle WebLogic Server",
            2048 to "dnp (distributed.net client)",
            102 to "MS Exchange",
            2000 to "Cisco SCCP (Skinny Client Control Protocol)",
            1723 to "PPTP (Point-to-Point Tunneling Protocol)",
            5000 to "UPnP (Universal Plug and Play)",
            5060 to "SIP (Session Initiation Protocol)",
            5061 to "SIP over TLS",
            8088 to "Radar",
            8089 to "Splunk",
            8082 to "JBOSS",
            8090 to "Apache NiFi"
        )
    }

    // code
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_port_scan)

        recyclerView = findViewById(R.id.recycler_view)
        textIp = findViewById(R.id.text_ip)
        buttonStart = findViewById(R.id.button)
        filterLayout = findViewById(R.id.filterLayout)

        // 开关按钮
        buttonStart.setOnClickListener {
            val ip = textIp.text.toString()

            // 数据筛选
            if (!NetworkUtils.isIPAddress(ip)) {
                Toast.makeText(this, "IP格式不对", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!::currentIp.isInitialized) { // 如果是第一次扫描
                currentIp = ip
            } else if (currentIp != ip) { // 不是第一次扫描 且 更换了IP
                // TODO: 对话框是异步的, 且PositiveButton方法中无法使用return让后面的代码不执行
                /*AlertDialog.Builder(this)
                    .setTitle(title)
                    .setMessage("检测到IP和前一次扫描的IP不同, 是否清空之前的记录?")
                    .setPositiveButton("取消") { dialog, _ ->
                        dialog.dismiss()
                        return@setPositiveButton
                    }
                    .setNegativeButton("确定") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                    .show()*/

                currentIp = ip
                currentPort = 1
                list.clear()
            }

            // 开启任务
            if (!status) {
                status = true
                buttonStart.text = "暂停"
                Toast.makeText(this, "正在扫描中", Toast.LENGTH_SHORT).show()

            } else { // 暂停任务
                status = false
                job?.cancel()
                buttonStart.text = "扫描"
                Toast.makeText(this, "已暂停", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // kotlin协程
            job = GlobalScope.launch(Dispatchers.IO) {
                // 在后台执行耗时操作
                while (currentPort < 65535) {
                    val map = HashMap<String, Any>()
                    map["port"] = currentPort
                    map["status"] = NetworkUtils.isOpen(ip, currentPort)
                    list.add(map)
                    // 在主线程中更新 UI 视图
                    withContext(Dispatchers.Main) {
                        // 通知适配器数据发生了改变
                        recyclerView.adapter?.notifyDataSetChanged()
                        recyclerView.scrollToPosition(recyclerView.adapter?.itemCount!! - 1)
                        //
                        currentPort++
                    }
                }

                job = null
            }

            // AsyncTask
            /*val instance = object : AsyncTask<Void, HashMap<String, Any>, String>() {
                    override fun doInBackground(vararg params: Void?): String {
                        // 在后台执行耗时操作
                        for (port in 70..90) {
                            Log.i(TAG, "onCreate: start: $port")
                            val map = HashMap<String, Any>()
                            map["port"] = port
                            map["status"] = Network.isOpen(textIp?.text.toString(), port)
                            list.add(map)
                            publishProgress(map)
                        }
                        return "Result"
                    }

                    override fun onProgressUpdate(vararg values: HashMap<String, Any>) {
                        // 在这里进行 UI 视图的修改操作，这个方法会在主线程中执行
                        // 通知适配器数据发生了改变
                        val map = values[0]
                        list.add(map)
                        recyclerView?.adapter?.notifyDataSetChanged()
                    }
                }
                instance.execute()*/

            // Thread
            /*Thread {
                    for (port in 70..90) {
                        Log.i(TAG, "onCreate: start: $port")
                        val map = HashMap<String, Any>()
                        map["port"] = port
                        map["status"] = Network.isOpen(textIp?.text.toString(), port)
                        list.add(map)
                        // 通知适配器数据发生了改变
                        recyclerView?.adapter?.notifyDataSetChanged()
                    }
                }.start()*/
        }

        //
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = RecycleAdapter(this, list)

        //
        filterLayout.setOnCheckedChangeListener { group, checkedId ->
            val rb = group.findViewById<RadioButton>(checkedId)
            when (checkedId) {
                R.id.filter_default -> {
                    list = list.sortedByDescending { (it["port"] as? String)?.toInt() }.toCollection(ArrayList())
                }
                R.id.filter_open -> {
                    list = list.sortedBy { it["status"] as? Boolean }.toCollection(ArrayList())
                }
                R.id.filter_close -> {
                    list = list.sortedByDescending { it["status"] as? Boolean }.toCollection(ArrayList())
                }
                R.id.filter_common -> {
                }

                else -> {
                    Toast.makeText(this, "你选择了: $group : $checkedId : ${rb.text}", Toast.LENGTH_SHORT).show()
                }
            }

            Toast.makeText(this, "${rb.text} 成功", Toast.LENGTH_SHORT).show()
//            recyclerView.adapter?.notifyItemRangeChanged(0, list.size)
//            recyclerView.adapter?.notifyItemMoved(0, list.size-1)
//            recyclerView.adapter?.notifyDataSetChanged()
            Log.i("2042", "onCreate: $list")

        }

    }

    // adapter
    class RecycleAdapter(
        private val context: Context,
        private val list: ArrayList<HashMap<String, Any>>
    ) : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater =
                LayoutInflater.from(context).inflate(R.layout.item_port_info, parent, false)
            return ViewHolder(inflater!!)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = list[position]

            if (item.get("status").toString() == "true") {
                holder.success?.text = "开启"
                holder.success?.setTextColor(Color.GREEN)
            } else {
                holder.success?.text = "关闭"
                holder.success?.setTextColor(Color.RED)
            }

            holder.port?.text = item["port"].toString()
            holder.desc?.text = portMap[item["port"]] ?: "暂无介绍"
        }

        override fun getItemCount(): Int {
            return list.size
        }

        // holder
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var success: TextView? = null
            var port: TextView? = null
            var desc: TextView? = null

            init {
                success = itemView.findViewById<View>(R.id.success) as TextView
                port = itemView.findViewById<View>(R.id.port) as TextView
                desc = itemView.findViewById<View>(R.id.desc) as TextView
            }
        }
    }
}