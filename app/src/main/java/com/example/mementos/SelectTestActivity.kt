package com.example.mementos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SelectTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_psy_test)
        val listView = findViewById<ListView>(R.id.test_list_view)

        val jsonFileString = getJsonDataFromAsset(this, "tests.json")
        Log.i("data", jsonFileString)
        val gson = Gson()
        val listTestType = object : TypeToken<List<Test>>() {}.type
        val listTests: List<Test> = gson.fromJson(jsonFileString, listTestType)

        val adapter = TestAdapter(this, listTests)
        listView.adapter = adapter
    }
}
