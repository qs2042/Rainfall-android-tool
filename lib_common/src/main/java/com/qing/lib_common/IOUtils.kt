package com.qing.lib_common

import android.os.Environment
import android.os.StatFs
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class IOUtils {
    companion object {

        @JvmStatic
        fun toText(stream: InputStream): String {
            val reader = BufferedReader(InputStreamReader(stream))
            val response = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
            reader.close()
            return response.toString()
        }
    }
}