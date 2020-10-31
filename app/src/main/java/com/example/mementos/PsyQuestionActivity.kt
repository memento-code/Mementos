package com.example.mementos

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_psy_question.*

class PsyQuestionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var questions: Cursor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_psy_question)
        val test_id = intent.getIntExtra("id", 1)
        Log.i("Question Activity", "Test ID: $test_id")
        buttonChoice1.setOnClickListener(this)
        buttonChoice2.setOnClickListener(this)
        buttonChoice3.setOnClickListener(this)
        buttonChoice4.setOnClickListener(this)

        val controller = AppDBController(this)
        questions = controller.select_questions_by_test(test_id)!!
        questions.moveToFirst()
        questionTextView.text = getString(this.resources.getIdentifier(
            questions.getString(questions.getColumnIndexOrThrow("title_code")),
            "string",
            this.packageName
        ))
    }

    /**
     * Метод переписывается для более удобного назначения действий для нескольких кнопок
     */
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

    fun nextQuestion(choice: Int){
        if (!questions.isLast){
            questions.moveToNext()
            questionTextView.text = getString(this.resources.getIdentifier(
                questions.getString(questions.getColumnIndexOrThrow("title_code")),
                "string",
                this.packageName
            ))
            //TODO: Добавить логику для учета ответов
        }
        else {
            questions.close()
            Log.i("Question Activity", "close")
            //TODO: Добавить переход на страницу с результатами
        }

    }
}