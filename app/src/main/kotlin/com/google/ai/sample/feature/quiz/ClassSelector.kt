package com.google.ai.sample.feature.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ClassSelector(selectedClass: Int, onClassSelected: (Int) -> Unit) {
    val classLevels = (1..12).toList()
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text("Select Class Level")
        Spacer(modifier = Modifier.height(8.dp))
        Box {
            Button(onClick = { expanded = !expanded }) {
                Text("Class $selectedClass")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                classLevels.forEach { classLevel ->
                    DropdownMenuItem(onClick = {
                        onClassSelected(classLevel)
                        expanded = false
                    }) {
                        Text("Class $classLevel")
                    }
                }
            }
        }
    }
}

fun DropdownMenuItem(onClick: () -> Unit, interactionSource: @Composable () -> Unit) {

}
