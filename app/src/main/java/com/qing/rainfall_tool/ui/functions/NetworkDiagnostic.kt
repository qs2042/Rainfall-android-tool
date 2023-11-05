package com.qing.rainfall_tool.ui.functions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qing.rainfall_tool.R
import com.qing.rainfall_tool.utils.NetworkUtil
import kotlinx.coroutines.*

class NDRecycleAdapter(
    private val context: Context,
    private val list: ArrayList<Pojo>
) : RecyclerView.Adapter<NDRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater
                .from(context)
                .inflate(R.layout.item_kv, parent, false)
        return ViewHolder(inflater!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.key?.text = item.key
        holder.value?.text = item.value
    }

    override fun getItemCount(): Int {
        return list.size
    }

    // holder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var key: TextView? = null
        var value: TextView? = null

        init {
            key = itemView.findViewById<View>(R.id.tv_key) as TextView
            value = itemView.findViewById<View>(R.id.tv_value) as TextView
        }
    }

    // pojo
    class Pojo(val key: String, val value: String) {
        var order: Int = 0

        fun withOrder(order: Int): Pojo {
            this.order = order
            return this
        }
    }
}

class NetworkDiagnostic : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var list: ArrayList<NDRecycleAdapter.Pojo> = ArrayList()

    private fun init() {
        recyclerView = findViewById(R.id.recycler_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_network_diagnostic)

        init()

        // recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = NDRecycleAdapter(this, list)

        // 设备网络
        executeIP()
        executeMAC()
        executeStatus()
        executeRoutingList()
        executeNetworkConnections()
        executeListeningPorts()

        // 网络(测试)
        executeTraceroute()

        // 设备SIM
        executeIsMobileDataConnected()
        executeSIM()

        // 设备WIFI
        executeWifiConnected()
        executeWifiName()
        executeWifiRouterIpAddress()
        executeWifiMacAddress()

        // 排序一下
        list.sortBy {
            it.order
        }
        recyclerView.adapter?.notifyDataSetChanged()

//        NetworkUtils.getDNSAndSubnetMask(this)
//        Log.i("2042", "onCreate: netstat: ${NetworkUtils.ping("192.168.1.1")}")
    }

    @DelicateCoroutinesApi
    private fun executeIP() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = NetworkUtil.getDeviceIPv4Address()

            withContext(Dispatchers.Main) {
                list.add(NDRecycleAdapter.Pojo("IP", response))
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    @DelicateCoroutinesApi
    private fun executeMAC() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = NetworkUtil.getDeviceMac()

            withContext(Dispatchers.Main) {
                list.add(NDRecycleAdapter.Pojo("MAC", response))
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    @DelicateCoroutinesApi
    private fun executeStatus() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = NetworkUtil.getNetworkStatus(this@NetworkDiagnostic)

            withContext(Dispatchers.Main) {
                list.add(NDRecycleAdapter.Pojo("网络状态", response))
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    @DelicateCoroutinesApi
    private fun executeRoutingList() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = NetworkUtil.getDeviceRoutingList()

            withContext(Dispatchers.Main) {
                list.add(NDRecycleAdapter.Pojo("当前设备所有路由", response))
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    @DelicateCoroutinesApi
    private fun executeNetworkConnections() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = NetworkUtil.getDeviceRoutingList()

            withContext(Dispatchers.Main) {
                list.add(NDRecycleAdapter.Pojo("当前设备所有网络连接", response))
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    @DelicateCoroutinesApi
    private fun executeListeningPorts() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = NetworkUtil.getDeviceListeningPorts()

            withContext(Dispatchers.Main) {
                list.add(NDRecycleAdapter.Pojo("当前设备所有监听端口", response))
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }


    @DelicateCoroutinesApi
    private fun executeIsMobileDataConnected() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = NetworkUtil.isMobileDataConnected(this@NetworkDiagnostic)

            withContext(Dispatchers.Main) {
                list.add(NDRecycleAdapter.Pojo("是否连接移动数据网络", response.toString()).withOrder(2))
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    @DelicateCoroutinesApi
    private fun executeSIM() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = NetworkUtil.sim(this@NetworkDiagnostic)

            withContext(Dispatchers.Main) {
                response?.let { list.add(NDRecycleAdapter.Pojo("SIM 运营商", it).withOrder(2)) }
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }


    @DelicateCoroutinesApi
    private fun executeTraceroute() {
        // 开启协程
        GlobalScope.launch(Dispatchers.IO) {
//            val response = NetworkUtils.traceroute("www.baidu.com")
//            val response = NetworkUtils.traceroute("39.156.66.10")
            val response = "无权限执行"

            // 主线程中进行UI操作
            withContext(Dispatchers.Main) {
                list.add(NDRecycleAdapter.Pojo("traceroute", response).withOrder(1))
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }


    @DelicateCoroutinesApi
    private fun executeWifiConnected() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = NetworkUtil.isWiFiConnected(this@NetworkDiagnostic)

            withContext(Dispatchers.Main) {
                list.add(NDRecycleAdapter.Pojo("是否连接WiFi", response.toString()).withOrder(3))
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    @DelicateCoroutinesApi
    private fun executeWifiName() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = NetworkUtil.getConnectedWifiName(this@NetworkDiagnostic)

            withContext(Dispatchers.Main) {
                list.add(NDRecycleAdapter.Pojo("WiFi名称", response.toString()).withOrder(3))
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    @DelicateCoroutinesApi
    private fun executeWifiRouterIpAddress() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = NetworkUtil.getConnectedWifiRouterIpAddress(this@NetworkDiagnostic)

            withContext(Dispatchers.Main) {
                list.add(NDRecycleAdapter.Pojo("WiFi路由器 IP", response.toString()).withOrder(3))
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    @DelicateCoroutinesApi
    private fun executeWifiMacAddress() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = NetworkUtil.getConnectedWifiMacAddress(this@NetworkDiagnostic)

            withContext(Dispatchers.Main) {
                list.add(NDRecycleAdapter.Pojo("WiFi路由器 MAC", response.toString()).withOrder(3))
                recyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

}
