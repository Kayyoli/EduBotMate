package com.google.ai.sample.feature.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun QuizScreen(viewModel: QuizViewModel = viewModel()) {
    var selectedClass by remember { mutableStateOf(1) }
    var numberOfQuestions by remember { mutableStateOf(5) }
    val quizState by viewModel.quizState.collectAsState()

    // Load questions when the screen is first displayed
    LaunchedEffect(selectedClass, numberOfQuestions) {
        viewModel.loadQuestions(selectedClass, numberOfQuestions)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        ClassSelector(selectedClass = selectedClass) { newClass ->
            selectedClass = newClass
            viewModel.loadQuestions(selectedClass, numberOfQuestions)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Select number of questions:")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = numberOfQuestions.toString(),
            onValueChange = { newValue ->
                numberOfQuestions = newValue.toIntOrNull() ?: 5
            },
            label = { Text("Number of Questions") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(onDone = {
                viewModel.loadQuestions(selectedClass, numberOfQuestions)
            })
        )
        Spacer(modifier = Modifier.height(16.dp))

        when (quizState) {
            is QuizUiState.Initial -> {
                // Show introductory content or message
                Text("Welcome to the Quiz!")
            }
            is QuizUiState.Loading -> {
                // Show loading message
                Text("Loading questions...")
            }
            is QuizUiState.Loaded -> {
                val questions = (quizState as QuizUiState.Loaded).questions

                // Make the list of questions scrollable
                LazyColumn(
                    modifier = Modifier
                        .weight(1f) // Make LazyColumn take available space
                        .padding(bottom = 16.dp)
                ) {
                    items(questions) { question ->
                        Column(modifier = Modifier.padding(vertical = 8.dp)) {
                            Text(text = question.questionText, style = MaterialTheme.typography.bodyLarge)
                            question.options.forEachIndexed { index, option ->
                                Row(modifier = Modifier.padding(vertical = 4.dp)) {
                                    RadioButton(
                                        selected = question.selectedOptionIndex == index,
                                        onClick = {
                                            viewModel.updateQuestionSelection(question.id, index)
                                        }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(option)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Ensure the submit button is always visible
                Button(
                    onClick = {
                        val answers = questions.associate { it.id to it.selectedOptionIndex }
                        viewModel.submitAnswers(answers)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Submit")
                }
            }
            is QuizUiState.Results -> {
                // Show the results
                val score = (quizState as QuizUiState.Results).score
                Text(text = "Your score: $score", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
