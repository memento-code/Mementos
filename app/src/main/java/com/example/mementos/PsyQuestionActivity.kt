package com.example.mementos

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_psy_question.*

class PsyQuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var questions: Cursor
    private var pointsByButtons = mutableMapOf(1 to 0, 2 to 0, 3 to 0, 4 to 0)
    private var testId= 0
    private var points = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_psy_question)
        testId = intent.getIntExtra("id", 1)
        Log.i("Question Activity", "Test ID: $testId")
        buttonChoice1.setOnClickListener(this)
        buttonChoice2.setOnClickListener(this)
        buttonChoice3.setOnClickListener(this)
        buttonChoice4.setOnClickListener(this)

        val controller = AppDBController(this)
        questions = controller.select_questions_by_test(testId)!!
        questions.moveToFirst()
        updateViews()
    }


    override fun onClick(v: View) {
        when (v.id){
            R.id.buttonChoice1 -> {
                Log.i("Question Activity", "Click choice #1")
                nextQuestion(1)
            }
            R.id.buttonChoice2 -> {
                Log.i("Question Activity", "Click choice #2")
                nextQuestion(2)
            }
            R.id.buttonChoice3 -> {
                Log.i("Question Activity", "Click choice #3")
                nextQuestion(3)
            }
            R.id.buttonChoice4 -> {
                Log.i("Question Activity", "Click choice #4")
                nextQuestion(4)
            }
            //TODO: добавить кнопку выхода из теста
        }
    }

    private fun nextQuestion(choice: Int){
        if (!questions.isLast){
            questions.moveToNext()
            points += pointsByButtons[choice]!!
            Log.i("Question Activity", "Points: $points")
            updateViews()
        }
        else {
            questions.close()
            Log.i("Question Activity", "close")
            //TODO: Добавить переход на страницу с результатами
        }

    }

    /**
     * Изменение значений для вопроса и кнопок. Поскольку в зависимости от вопроса кол-во вариантов
     * ответа может быть несколько, то лишние кнопки скрываются с экрана.
     */
    private fun updateViews(){
        questionTextView.text = getStringByCursor(questions, "title_code")

        val controller = AppDBController(this)
        val choices = controller.select_choices(testId, questions.getInt(questions.getColumnIndexOrThrow("id")))!!
        val buttons = mutableListOf(R.id.buttonChoice1, R.id.buttonChoice2, R.id.buttonChoice3, R.id.buttonChoice4)
        while (choices.moveToNext()){
            when (choices.position){
                0 -> {
                    buttonChoice1.text = getStringByCursor(choices, "name_id")
                    pointsByButtons[1] = choices.getInt(choices.getColumnIndexOrThrow("points"))
                    buttons.remove(R.id.buttonChoice1)
                }
                1 -> {
                    buttonChoice2.text = getStringByCursor(choices, "name_id")
                    pointsByButtons[2] = choices.getInt(choices.getColumnIndexOrThrow("points"))
                    buttons.remove(R.id.buttonChoice2)
                }
                2 -> {
                    buttonChoice3.text = getStringByCursor(choices, "name_id")
                    pointsByButtons[3] = choices.getInt(choices.getColumnIndexOrThrow("points"))
                    buttonChoice3.visibility = View.VISIBLE
                    buttons.remove(R.id.buttonChoice3)
                }
                3 -> {
                    buttonChoice4.text = getStringByCursor(choices, "name_id")
                    pointsByButtons[4] = choices.getInt(choices.getColumnIndexOrThrow("points"))
                    buttonChoice4.visibility = View.VISIBLE
                    buttons.remove(R.id.buttonChoice4)
                }
            }
        }

        //Скрытие лишних кнопок, поскольку кол-во вариантов может быть разным
        for (j in buttons){
            val button = findViewById<Button>(j)
            button.visibility = View.GONE
            pointsByButtons[j] = 0
        }
        choices.close()
    }

    /**
     * Получение строки из XML файла по курсору и ID колонки
     * @param cursor - инициализированный курсор из класса SQLController
     * @param column_id - идентификатор колонки в курсоре, из которого будет извлекаться ID строки
     * @return - строка, полученная из XML-файла
     */
    private fun getStringByCursor(cursor: Cursor, column_id: String): String {
        return getString(this.resources.getIdentifier(
            cursor.getString(cursor.getColumnIndexOrThrow(column_id)),
            "string",
            this.packageName
        ))
    }
}