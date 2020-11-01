package com.example.mementos

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

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
    fun selectAllTests(): Cursor? {
        return sqLiteDatabase!!.rawQuery("SELECT id, title_code, type, time_spent_sec, question_cnt " +
                "FROM core_psy_test ORDER BY id", null)
    }

    /**
     * @param testId ID теста из таблицы core_psy_test
     * @return Курсор, содержащий вопросы к выбранному тесту
     */
    fun selectQuestionsByTest(testId: Int): Cursor? {
        return sqLiteDatabase!!.rawQuery("SELECT id, title_code, drawable_dest, test_id " +
                "FROM psy_test_question WHERE test_id = ? ORDER BY id",
                arrayOf(testId.toString()))
    }

    /**
     * Получение списка доступных вариантов ответа для вопроса
     * @param testId - ID теста из таблицы core_psy_test
     * @param questionId - ID вопроса из таблицы psy_test_question
     * @return - Курсор, содержащий варианты ответов к вопросу
     */
    fun selectChoices(testId: Int, questionId: Int): Cursor? {
        return sqLiteDatabase!!.rawQuery("SELECT name_id, points FROM psy_test_choices " +
                "WHERE test_id = ? AND question_id = ?",
                arrayOf(testId.toString(), questionId.toString())
        )
    }

    /**
     * Получение результатов теста
     * @param testId - ID теста из таблицы core_psy_test
     * @param points - сумма баллов, набранная в ходе прохождения теста
     * @return - Курсор, содежащий результаты прохождения теста
     */
    fun selectResult(testId: Int, points: Int): Cursor? {
        return sqLiteDatabase!!.rawQuery("SELECT result_text FROM psy_test_result " +
                "WHERE id_test = ? AND ? BETWEEN sum_points_min AND sum_points_max",
            arrayOf(testId.toString(), points.toString())
        )
    }
}