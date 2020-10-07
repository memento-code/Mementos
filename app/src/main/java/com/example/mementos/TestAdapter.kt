package com.example.mementos

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class TestAdapter(private val context: Context,
                    private val dataSource: List<Test>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.list_item_test, parent, false)
        val titleTest = rowView.findViewById(R.id.titleTest) as TextView
        val countQuestionsTest = rowView.findViewById(R.id.countQuestionsTest) as TextView
        val timeTest = rowView.findViewById(R.id.timeTest) as TextView
        val typeImageTest =  rowView.findViewById(R.id.typeImageTest) as ImageView

        val test = getItem(position) as Test
        countQuestionsTest.text = "${test.question_count} вопросов"
        timeTest.text = "${test.time} минут"
        titleTest.text = test.title

        when (test.type){
            "questions" -> typeImageTest.setImageResource(R.drawable.button_selector)
        }

        return rowView
    }
}