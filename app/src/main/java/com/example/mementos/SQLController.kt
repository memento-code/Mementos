package com.example.mementos

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

/**
 * Класс для управления БД
 */
open class AppDBController(context: Context) {
    private val sqLiteDatabase: SQLiteDatabase?

    init {
        val appAssetsSQLite = AppAssetsSQLite(context)
        sqLiteDatabase = appAssetsSQLite.writableDatabase
    }

    /**
     * @return Курсор, содержащий все доступные тесты. Список колонок лежит в AppData.Test
     */
    fun select_all_tests(): Cursor? {
        return sqLiteDatabase?.rawQuery("SELECT id, title_code, type, time_spent_sec, question_cnt " +
                "FROM core_psy_test ORDER BY id", null)
    }

    /**
     * @param test_id ID теста из таблицы core_psy_tests
     * @return Курсор, содержащий вопросы к выбранному тесту
     */
    fun select_questions_by_test(test_id: Int): Cursor? {
        return sqLiteDatabase?.rawQuery("SELECT id, title_code, drawable_dest, test_id " +
                "FROM psy_test_question WHERE test_id = ? ORDER BY id",
                arrayOf(test_id.toString()))
    }

    fun select_choices(test_id: Int, question_id: Int): Cursor? {
        return sqLiteDatabase?.rawQuery("SELECT name_id, points FROM psy_test_choices " +
                "WHERE test_id = ? AND question_id = ?",
                arrayOf(test_id.toString(), question_id.toString())
        )
    }
}