package com.google.ai.sample.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.ai.sample.R // Make sure this import statement matches your package name and structure

@Composable
fun WelcomeScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(120.dp) // Adjust the size as needed
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = "Welcome!",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "Personalised Edubot that enhances and improves self-regulated learning.",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center, // Center-align the text
                    modifier = Modifier
                        .fillMaxWidth() // Ensure the text takes up the full width of the parent
                        .padding(bottom = 32.dp)
                )
                Button(onClick = { navController.navigate("menu") }) {
                    Text(text = "Get Started")
                }
            }
        }
    }
}

