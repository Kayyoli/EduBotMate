package com.google.ai.sample.feature.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class QuizViewModel : ViewModel() {
    private val _quizState = MutableStateFlow<QuizUiState>(QuizUiState.Initial)
    val quizState: StateFlow<QuizUiState> = _quizState.asStateFlow()

    private val allQuestions = listOf(
        QuizQuestion(1, "What is 2 + 2?", listOf("3", "4", "5")),
        QuizQuestion(2, "What is the capital of France?", listOf("Berlin", "Madrid", "Paris")),
        QuizQuestion(3, "What is the boiling point of water?", listOf("90°C", "100°C", "110°C")),
        QuizQuestion(4, "Which planet is known as the Red Planet?", listOf("Earth", "Mars", "Jupiter")),
        QuizQuestion(5, "What is the largest ocean?", listOf("Atlantic", "Pacific", "Indian"))
    )

    private val correctAnswers = mapOf(
        1 to 1, // Correct answer index for question ID 1
        2 to 2, // Correct answer index for question ID 2
        3 to 1, // Correct answer index for question ID 3
        4 to 1, // Correct answer index for question ID 4
        5 to 1  // Correct answer index for question ID 5
    )

    fun loadQuestions(selectedClass: Int, numberOfQuestions: Int) {
        viewModelScope.launch {
            _quizState.value = QuizUiState.Loading
            delay(1000) // Simulating network delay

            val randomQuestions = allQuestions.shuffled().take(numberOfQuestions)
            _quizState.value = QuizUiState.Loaded(randomQuestions)
        }
    }

    fun submitAnswers(answers: Map<Int, Int>) {
        viewModelScope.launch {
            val score = answers.entries.count { (questionId, selectedOptionIndex) ->
                correctAnswers[questionId] == selectedOptionIndex
            }
            _quizState.value = QuizUiState.Results(score)
        }
    }

    fun updateQuestionSelection(questionId: Int, selectedOptionIndex: Int) {
        _quizState.value = when (val currentState = _quizState.value) {
            is QuizUiState.Loaded -> {
                val updatedQuestions = currentState.questions.map { question ->
                    if (question.id == questionId) {
                        question.copy(selectedOptionIndex = selectedOptionIndex)
                    } else {
                        question
                    }
                }
                QuizUiState.Loaded(updatedQuestions)
            }
            else -> currentState
        }
    }
}
