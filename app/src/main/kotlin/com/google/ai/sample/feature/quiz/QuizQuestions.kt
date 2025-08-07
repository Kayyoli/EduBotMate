package com.google.ai.sample.feature.quiz

data class QuizQuestion(
    val id: Int,
    val questionText: String,
    val options: List<String>,
    var selectedOptionIndex: Int = -1
)
