package com.example.mementos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView


class SelectTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_psy_test)
        val listView = findViewById<ListView>(R.id.test_list_view)

        val controller = AppDBController(this)
        val allTests = controller.selectAllTests()
        val listItems = ArrayList<Test>()

        with(allTests!!){
            while(moveToNext()){
                val titleCode = resources.getString(this@SelectTestActivity.resources.getIdentifier(
                    getString(getColumnIndexOrThrow("title_code")),
                    "string",
                    this@SelectTestActivity.packageName
                ))

                listItems.add(
                    Test(
                        id = getInt(getColumnIndexOrThrow("id")),
                        title_code = titleCode,
                        question_cnt = getInt(getColumnIndexOrThrow("question_cnt")),
                        time_spent_sec = getInt(getColumnIndexOrThrow("time_spent_sec")),
                        type = getString(getColumnIndexOrThrow("type"))
                    )
                )
            }
        }

        val adapter = TestAdapter(this, listItems)
        listView.adapter = adapter
    }
}
