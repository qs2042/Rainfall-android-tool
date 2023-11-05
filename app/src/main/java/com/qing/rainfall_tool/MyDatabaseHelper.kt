package com.qing.rainfall_tool

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory

import android.database.sqlite.SQLiteOpenHelper


class MyDatabaseHelper(
    val context: Context, name: String?,
    factory: CursorFactory?, version: Int
) :
    SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
//        db.execSQL(CREATE_BOOK)
//        db.execSQL(CREATE_CATEGORY)
        db.execSQL(CREATE_USER)
        // Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        db.execSQL("drop table if exists Book")
//        db.execSQL("drop table if exists Category")
        db.execSQL("drop table if exists User")
        onCreate(db)
    }

    companion object {
        const val CREATE_BOOK = ("create table Book ("
                + "id integer primary key autoincrement, "
                + "author text, "
                + "price real, "
                + "pages integer, "
                + "name text)")
        const val CREATE_CATEGORY = ("create table Category ("
                + "id integer primary key autoincrement, "
                + "category_name text, "
                + "category_code integer)")
        const val CREATE_USER = ("create table User ("
                + "id integer primary key autoincrement, "
                + "user_name text, "
                + "user_age integer, "
                + "user_sex integer)")
    }
}
