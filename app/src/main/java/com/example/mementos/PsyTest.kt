package com.example.mementos

import com.google.gson.JsonArray
import com.google.gson.JsonObject

data class Test(
    val id: Int,
    val title: String,
    val question_count: Int,
    val time: Int,
    val questions: List<JsonObject>,
    val type: String
){}