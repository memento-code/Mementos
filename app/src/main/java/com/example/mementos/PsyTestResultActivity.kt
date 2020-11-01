package com.example.mementos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PsyTestResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_psy_test_result)
        var points = intent.getIntExtra("points", 0)
        val controller = AppDBController(this)
        //val testResult =
    }
}