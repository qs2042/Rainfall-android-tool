package com.qing.lib_common

import android.os.Environment
import android.os.StatFs
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader

data class StorageInfo(
    val internalStoragePath: String,
    val totalMemory: Long,
    val usedMemory: Long,
    val availableMemory: Long
) {
    fun getTotalMemoryByString(): String {
        return formatMemorySize(totalMemory)
    }

    fun getUsedMemoryByString(): String {
        return formatMemorySize(usedMemory)
    }

    fun getAvailableMemoryByString(): String {
        return formatMemorySize(availableMemory)
    }

    private fun formatMemorySize(size: Long): String {
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        var bytes = size.toDouble()
        var unitIndex = 0
        while (bytes > 1024 && unitIndex < units.size - 1) {
            bytes /= 1024
            unitIndex++
        }
        val formattedSize = String.format("%.2f", bytes)
        return "$formattedSize ${units[unitIndex]}"
    }
}

class FileUtils {
    companion object {
        // 获取总内存大小的函数
        private fun getTotalInternalMemorySize(): Long {
            val statFs = StatFs(Environment.getDataDirectory().path)
            return statFs.totalBytes
        }

        // 获取已用内存大小的函数
        private fun getUsedInternalMemorySize(): Long {
            val statFs = StatFs(Environment.getDataDirectory().path)
            val totalBytes = statFs.totalBytes
            val availableBytes = statFs.availableBytes
            return totalBytes - availableBytes
        }

        // 获取剩余内存大小的函数
        private fun getAvailableInternalMemorySize(): Long {
            val statFs = StatFs(Environment.getDataDirectory().path)
            return statFs.availableBytes
        }

        // 获取所有文件的名称
        fun getAllFileNames(directoryPath: String): List<String> {
            val fileNames = mutableListOf<String>()
            val directory = File(directoryPath)

            if (directory.exists() && directory.isDirectory) {
                val files = directory.listFiles()
                if (files != null) {
                    for (file in files) {
                        if (file.isFile) {
                            fileNames.add(file.name)
                        }
                    }
                }
            }

            return fileNames
        }

        // 获取内部存储的已用和可用内存
        @JvmStatic
        fun getStorageInfo(): StorageInfo {
            // 获取内部存储路径
            val internalStoragePath = Environment.getExternalStorageDirectory().absolutePath

            // 获取总内存大小
            val totalMemory = getTotalInternalMemorySize()

            // 获取已用内存大小
            val usedMemory = getUsedInternalMemorySize()

            // 获取剩余内存大小
            val availableMemory = getAvailableInternalMemorySize()

            // 创建 StorageInfo 实例
            val storageInfo = StorageInfo(
                internalStoragePath,
                totalMemory,
                usedMemory,
                availableMemory
            )
            return storageInfo
        }
    }
}