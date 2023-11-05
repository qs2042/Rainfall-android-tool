package com.qing.rainfall_tool.content_provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.qing.rainfall_tool.MyDatabaseHelper


class MyContentProvider: ContentProvider() {
    private lateinit var dbHelper: MyDatabaseHelper

    companion object {
        // 定义URI与数据表的映射关系
        /*
        AUTHORITY   身份
        path        表
        code        id

        使用了基于RESTful风格的设计模式, 一个表有一个dir和item
        原来:
        addURI(AUTHORITY, "user-add", 1)
        addURI(AUTHORITY, "user-delete", 2)
        addURI(AUTHORITY, "user-update", 3)
        addURI(AUTHORITY, "user-select", 4)
        现在:
        addURI(AUTHORITY, "user", 1)    // dir
        addURI(AUTHORITY, "user/#", 2)  // item
        很明显可以看到item有一个特征, 那就是后面带着 /# 这表明有占位符
        如果开发者传入了 user/1 那么就是item, 如果传入了user/ or user 那么就是dir
         */
        private const val AUTHORITY = "com.qing.rainfall_tool.provider.one"
        private val URI_MATCHER = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, "book", 1)        // DIR
            addURI(AUTHORITY, "category", 2)    // DIR
            addURI(AUTHORITY, "system", 3)      // DIR
            addURI(AUTHORITY, "user", 4)        // DIR
            addURI(AUTHORITY, "user/#", 5)      // ITEM
        }

        // 根据URI获取数据表名称
        /*
        因为我使用了RESTful风格, 这个没有用了

        本来是为了获取这些: user-add, user-delete, user-update, user-select 的表名
         */
        private fun getTableName(uri: Uri): String {
            return when (URI_MATCHER.match(uri)) {
                1 -> "book"
                2 -> "category"
                3 -> "system"
                4 -> "user"
                5 -> "user"
                else -> throw IllegalArgumentException("Unsupported URI: $uri")
            }
        }
    }

    // 初始化
    override fun onCreate(): Boolean {
        dbHelper = MyDatabaseHelper(context!!, "TestStore.db", null, 1)
        return true
    }

    // 查询数据
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db = dbHelper.writableDatabase

        val cursor: Cursor? = when (URI_MATCHER.match(uri)) {
            1 -> db.query("Book", projection, selection, selectionArgs, null, null, sortOrder)
            2 -> db.query("Category", projection, selection, selectionArgs, null, null, sortOrder)
            3 -> db.query("System", projection, selection, selectionArgs, null, null, sortOrder)
            4 -> db.query("User", projection, selection, selectionArgs, null, null, sortOrder)
            5 -> {
                val userId = uri.pathSegments[1]
                db.query("User", projection, "id = ?", arrayOf(userId), null, null, sortOrder);
            }
            else -> null
        }

        /*// 创建要返回的Cursor对象
        val cursor = MatrixCursor(projection)

        // 添加数据行到Cursor
        val row1 = arrayOf<Any>("张三", "18", "男")
        cursor.addRow(row1)

        val row2 = arrayOf<Any>("李四", "81", "女")
        cursor.addRow(row2)*/

        // 设置通知URI，以便当数据发生变化时通知观察者
        cursor?.setNotificationUri(context?.contentResolver, uri)

        return cursor
    }

    // 返回MIME类型
    override fun getType(uri: Uri): String? {
        return when (URI_MATCHER.match(uri)) {
            1 -> "vnd.android.cursor.dir/vnd.com.qing.rainfall_tool.databasetest.provider.book"
            2 -> "vnd.android.cursor.dir/vnd.com.qing.rainfall_tool.databasetest.provider.category"
            3 -> "vnd.android.cursor.dir/vnd.com.qing.rainfall_tool.databasetest.provider.system"
            4 -> "vnd.android.cursor.dir/vnd.com.qing.rainfall_tool.databasetest.provider.user"
            5 -> "vnd.android.cursor.item/vnd.com.qing.rainfall_tool.databasetest.provider.user"
            else -> null
        }
    }

    // 插入数据
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbHelper.writableDatabase
        val uriReturn: Uri? =  when (URI_MATCHER.match(uri)) {
            1 -> null
            2 -> null
            3 -> null
            4 -> {
                val newUserId = db.insert("User", null, values);
                Uri.parse("content://$AUTHORITY/user/$newUserId");
            }
            5 -> null
            else -> null
        }

        return uriReturn
    }

    // 删除数据
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = dbHelper.writableDatabase
        val deletedRows = when (URI_MATCHER.match(uri)) {
            1 -> db.delete("Book", selection, selectionArgs)
            2 -> db.delete("Category", selection, selectionArgs)
            3 -> db.delete("System", selection, selectionArgs)
            4 -> db.delete("User", selection, selectionArgs)
            5 -> {
                val userId = uri.pathSegments[1]
                db.delete("User", "id = ?", arrayOf(userId))
            }
            else -> 0
        }
        return deletedRows
    }

    // 更新数据
    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val db = dbHelper.writableDatabase
        val updatedRows = when (URI_MATCHER.match(uri)) {
            1 -> db.update("Book", values, selection, selectionArgs)
            2 -> db.update("Category", values, selection, selectionArgs)
            3 -> db.update("System", values, selection, selectionArgs)
            4 -> db.update("User", values, selection, selectionArgs)
            5 -> {
                val newUserId = uri.pathSegments[1]
                db.update("User", values, "id = ?", arrayOf(newUserId))
            }
            else -> 0
        }
        return updatedRows
    }
}