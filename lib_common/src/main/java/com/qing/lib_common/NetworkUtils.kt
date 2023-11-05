package com.qing.lib_common

import java.io.IOException
import java.net.HttpURLConnection
import java.net.Socket
import java.net.URL

class NetworkUtils {
    companion object {
        /**
         * 是否为网站链接
         */
        fun isWebsiteLink(text: String): Boolean {
            val domainRegex = Regex("http[s]?://\\S+")
            return domainRegex.matches(text)
        }

        /**
         * 是否为域名
         */
        @JvmStatic
        fun isDomainName(text: String): Boolean {
            val domainRegex = Regex("^([a-zA-Z0-9]+(-[a-zA-Z0-9]+)*\\.)+[a-zA-Z]{2,}$")
            return domainRegex.matches(text)
        }

        /**
         * 是否为IP
         */
        @JvmStatic
        fun isIPAddress(text: String): Boolean {
            // 字符串是否为空
            if (text.isEmpty()) {
                return false
            }

            // 如果是ipv4: x.x.x.x
            val regexV4 =
                Regex("^(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$")
            if (regexV4.matches(text)) {
                return true
            }

            // 如果是ipv6: xxxx:xxxx:xxxx:xxxx
            val regexV6 =
                Regex("^([0-9A-Fa-f]{0,4}:){0,1}[0-9A-Fa-f]{0,4}:[0-9A-Fa-f]{0,4}:[0-9A-Fa-f]{0,4}:[0-9A-Fa-f]{0,4}:[0-9A-Fa-f]{0,4}:[0-9A-Fa-f]{0,4}$")
            if (regexV6.matches(text)) {
                return true
            }

            return false
        }

        /**
         * 端口是否打开
         */
        @JvmStatic
        fun isOpen(ip: String, port: Int = 1): Boolean {
            // 1 - 65535
            try {
                val socket = Socket(ip, port)
                socket.close()
                return true
            } catch (e: IOException) {
                return false
            }
        }

        /**
         * 获取IP信息
         */
        fun getIPInfo(ip: String): String {
            val url = URL("https://ipinfo.io/$ip/json")
            val connection = url.openConnection() as HttpURLConnection
            if (connection.responseCode == 404) {
                return "请求失败"
            }
            return IOUtils.toText(connection.inputStream)
        }
    }
}