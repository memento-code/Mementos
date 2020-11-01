package com.example.mementos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_psy_test_result.*

class PsyTestResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_psy_test_result)
        val points = intent.getIntExtra("points", 0)
        val testId = intent.getIntExtra("test_id", 1)
        val controller = AppDBController(this)
        val resultTest = controller.selectResult(testId, points)!!
        Log.i("Result Activity", "${resultTest.count}, $testId $points")
        resultTest.moveToFirst()
        val resultId = resultTest.getString(resultTest.getColumnIndexOrThrow("result_text"))

        titleResultTextView.text = getString(resources.getIdentifier(
            "${resultId}_title",
            "string",
            this.packageName
        ))

        textResultTextView.text = getString(resources.getIdentifier(
            "${resultId}_text",
            "string",
            this.packageName
        ))

    }


}