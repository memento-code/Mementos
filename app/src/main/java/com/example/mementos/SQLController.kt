package com.example.mementos

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

open class AppDBController(context: Context) {
    private val sqLiteDatabase: SQLiteDatabase?

    init {
        val appAssetsSQLite = AppAssetsSQLite(context)
        sqLiteDatabase = appAssetsSQLite.writableDatabase
    }

    fun select_all_tests(): Cursor? {
        return sqLiteDatabase?.rawQuery("SELECT id, title, type,  time_spent_sec, question_cnt " +
                "FROM core_psy_tests", null)
    }
}