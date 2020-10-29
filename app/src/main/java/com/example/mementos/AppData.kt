package com.example.mementos

data class Test(
    val id: Int,
    val title: String,
    val question_cnt: Int,
    val time_spent_sec: Int,
    val type: String
)

data class Question(
    val id: Int,
    val test_id: Int,
    val drawable_dest: String
)