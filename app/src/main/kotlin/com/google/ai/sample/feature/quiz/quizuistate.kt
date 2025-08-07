package com.google.ai.sample.feature.quiz

sealed class QuizUiState {
    object Initial : QuizUiState()
    object Loading : QuizUiState()
    data class Loaded(val questions: List<QuizQuestion>) : QuizUiState()
    data class Results(val score: Int) : QuizUiState()
}
