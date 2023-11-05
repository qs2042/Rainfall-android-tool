package com.qing.rainfall_tool.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.wifi.ScanResult
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.AsyncTask
import android.telephony.TelephonyManager
import android.util.Log
import com.qing.lib_common.IOUtils
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException

/**
 * 网络工具
 *
 * 注意事项, 调用方法时记得开启异步/多线程/协程
 */
class NetworkUtil(private val context: Context) {

    companion object {
        var path = "/system/bin"

        /**
         * 获取当前设备的ip
         */
        fun getDeviceIPv4Address(): String {
            val interfaces = NetworkInterface.getNetworkInterfaces()
            while (interfaces.hasMoreElements()) {
                val networkInterface = interfaces.nextElement()
                val addresses = networkInterface.inetAddresses
                while (addresses.hasMoreElements()) {
                    val address = addresses.nextElement()
                    if (!address.isLoopbackAddress && address is InetAddress && address.hostAddress.indexOf(
                            ':'
                        ) < 0
                    ) {
                        return address.hostAddress
                    }
                }
            }
            return ""
        }

        /**
         * 获取当前设备的mac
         */
        fun getDeviceMac(): String {
            val networkInterfaces = NetworkInterface.getNetworkInterfaces()
            while (networkInterfaces.hasMoreElements()) {
                val networkInterface = networkInterfaces.nextElement()
                val macBytes = networkInterface.hardwareAddress

                if (macBytes != null) {
                    val macBuilder = StringBuilder()
                    for (b in macBytes) {
                        macBuilder.append(String.format("%02X:", b))
                    }
                    if (macBuilder.isNotEmpty()) {
                        macBuilder.deleteCharAt(macBuilder.length - 1)
                    }
                    return macBuilder.toString()
                }
            }
            return "获取失败"
        }

        /**
         * 是否连接了移动数据网络
         */
        fun isMobileDataConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_MOBILE && networkInfo.isConnected
        }

        /**
         * 获取sim卡运营商名称
         */
        fun sim(context: Context): String? {
            val telephonyManager =
                context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val simOperatorName = telephonyManager.simOperatorName
            return if (simOperatorName == "") "获取失败" else simOperatorName
        }

        /**
         * 获取当前设备的网络状态(是否可以接入因特网)
         */
        fun getNetworkStatus(context: Context): String {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return (networkInfo != null && networkInfo.isConnected).toString()
        }

        // traceroute TODO: 模拟器正常运行, 真机上会闪退, 感觉像是没有权限? 之后看看日志
        fun traceroute(host: String): String {
            // -m 20 指定最大的跃点数（最大的路由器跳数）为20。traceroute将最多跟踪和显示20个路由器跳跃的信息。
            // -w 5 指定每个跃点的等待时间为5秒。在每个跃点上，traceroute将等待5秒钟以获取响应。
            // $host 占位符，表示要执行traceroute操作的目标主机
            // 32 设置每个跃点发送的数据包的大小（单位为字节）为32字节。
            val process = Runtime.getRuntime().exec("/system/bin/traceroute -m 20 $host 32")

            return process.inputStream?.let {
                val s = IOUtils.toText(it)
                if (s != "") s else "获取结果为空"
            } ?: "获取结果失败"
        }

        /**
         * 获取本机路由表
         * @see netstat
         */
        fun getDeviceRoutingList(): String {
            return netstat("-rn")
        }

        /**
         * 获取本机所有网络连接
         * @see netstat
         */
        fun getDeviceNetworkConnections(): String {
            return netstat("-an")
        }

        /**
         * 获取本机监听的端口
         * @see netstat
         */
        fun getDeviceListeningPorts(): String {
            return netstat("-tlun")
        }

        // 包装netstat命令 TODO: 之后改为build模式
        private fun netstat(args: String): String {
            // -t TCP协议
            // -u UDP协议
            // -a 已建立的连接
            // -l 监听
            // -r 路由
            // -n 显示IP地址和端口号
            // -p 显示进程ID/名称
            // -c 以连续更新的方式显示网络连接
            // -e 显示所有连接及其相关的程序信息（需要root权限）
            val process = Runtime.getRuntime().exec("/system/bin/netstat $args")

            return process.inputStream?.let {
                val s = IOUtils.toText(it)
                if (s != "") s else "获取结果为空"
            } ?: "获取结果失败"
        }

        /**
         * 是否连接WiFi
         */
        fun isWiFiConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_WIFI && networkInfo.isConnected
        }

        /**
         * 获取WiFi的名称
         * @see getConnectedWifiInfo
         */
        fun getConnectedWifiName(context: Context): String? {
            val wifiInfo = getConnectedWifiInfo(context)
            return wifiInfo?.ssid
        }

        /**
         * 获取WiFi的IP
         * @see getConnectedWifiInfo
         * @see intToByteArray
         */
        fun getConnectedWifiRouterIpAddress(context: Context): String? {
            val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager
            val dhcpInfo = wifiManager?.dhcpInfo
            val ipAddress = dhcpInfo?.gateway ?: 0
            return if (ipAddress == 0) null else InetAddress.getByAddress(intToByteArray(ipAddress)).hostAddress
        }

        /**
         * 获取WiFi的MAC TODO: 好像有点问题
         * @see getConnectedWifiInfo
         */
        fun getConnectedWifiMacAddress(context: Context): String? {
            val wifiInfo = getConnectedWifiInfo(context)
            return wifiInfo?.macAddress
        }

        // 获取WiFi信息
        private fun getConnectedWifiInfo(context: Context): WifiInfo? {
            val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            if (wifiManager.isWifiEnabled) {
                val wifiInfo = wifiManager.connectionInfo
                if (wifiInfo.networkId != -1) {
                    return wifiInfo
                }
            }
            return null
        }

        // 将整数转换为字节数组
        private fun intToByteArray(value: Int): ByteArray {
            return byteArrayOf(
                (value and 0xFF).toByte(),
                (value shr 8 and 0xFF).toByte(),
                (value shr 16 and 0xFF).toByte(),
                (value shr 24 and 0xFF).toByte()
            )
        }

        // TODO: 获取 WIFI信息(设备和路由器之间的 dbm值, mbps值, Mhz值, 信道)

        // TODO: 获取 WIFI信息(扫描局域网设备)
        // TODO: 获取 WIFI信息(信道图)
        // TODO: 获取 WIFI信息(热点列表: 名称, 设备, 强度, 频率, 信道, mac)
        fun wifi(context: Context) {
            val wifiManager =
                context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val wifiInfo: WifiInfo? = wifiManager.connectionInfo

            val ssid = wifiInfo?.ssid
            val bssid = wifiInfo?.bssid
            println("wifiInfo: $wifiInfo")
            println("ssid: $ssid")
            println("bssid: $bssid")
        }


        //获取 DNS服务器和子网掩码
        fun getDNSAndSubnetMask(context: Context) {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: Network? = connectivityManager.activeNetwork
            val linkProperties = connectivityManager.getLinkProperties(activeNetwork)

            val dnsServers = linkProperties?.dnsServers
            val subnetMask = getSubnetMask(linkProperties?.interfaceName)

            // 打印 DNS 服务器和子网掩码
            dnsServers?.forEachIndexed { index, dns ->
                Log.d("DNS", "DNS Server $index: $dns")
            }
            Log.d("Subnet Mask", subnetMask ?: "Unknown")
        }

        private fun getSubnetMask(interfaceName: String?): String? {
            try {
                val networkInterface = NetworkInterface.getByName(interfaceName)
                val interfaceAddresses = networkInterface.interfaceAddresses
                for (interfaceAddress in interfaceAddresses) {
                    val subnetMask = interfaceAddress.networkPrefixLength
                    return convertPrefixLengthToMask(subnetMask)
                }
            } catch (e: SocketException) {
                e.printStackTrace()
            }
            return null
        }

        private fun convertPrefixLengthToMask(prefixLength: Short): String {
            val maskBits = (0xFFFFFFFF shl (32 - prefixLength)).toLong()
            return (maskBits shr 24 and 0xFF).toString() + "." +
                    (maskBits shr 16 and 0xFF).toString() + "." +
                    (maskBits shr 8 and 0xFF).toString() + "." +
                    (maskBits and 0xFF).toString()
        }

        // 热点
        fun hotPoint(context: Context) {
            val wifiManager =
                context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val scanResults: List<ScanResult> = wifiManager.scanResults

            for (scanResult in scanResults) {
                // 使用scanResult进行操作，比如获取热点名称、信号强度等信息
            }
        }

        // ping
        fun ping(host: String): String {
            val process = Runtime.getRuntime().exec("$path/ping -c 4 $host")

            return process.inputStream?.let {
                val s = IOUtils.toText(it)
                if (s != "") s else "获取结果为空"
            } ?: "获取结果失败"
        }

        // TODO: 获取信号强度(RSSI, RSPR, RSRQ, SINR)
    }

}
/*fun executePing(host: String) {
    PingTask().execute(host)
}

fun executeTraceroute(host: String) {
    TracerouteTask().execute(host)
}

private inner class PingTask : AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg params: String): String {
        val host = params[0]
        val process = Runtime.getRuntime().exec("/system/bin/ping -c 4 $host")
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val response = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            response.append(line).append("\n")
        }
        reader.close()
        return response.toString()
    }

    override fun onPostExecute(result: String) {
        // 在这里处理 Ping 的结果
    }
}

private inner class TracerouteTask : AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg params: String): String {
        val host = params[0]
        val process = Runtime.getRuntime().exec("/system/bin/traceroute -m 20 $host")
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val response = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            response.append(line).append("\n")
        }
        reader.close()
        return response.toString()
    }

    override fun onPostExecute(result: String) {
        // 在这里处理 Traceroute 的结果
    }
}*/
