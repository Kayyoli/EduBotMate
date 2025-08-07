package com.google.ai.sample.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = LightPurple,
    onPrimary = Black,
    primaryContainer = LightPurpleVariant,
    onPrimaryContainer = Black,
    secondary = LightPurple,
    onSecondary = Black,
    secondaryContainer = LightPurpleVariant,
    onSecondaryContainer = Black,
    background = Black,
    onBackground = LightPurple,
    surface = Black,
    onSurface = LightPurple,
)

private val LightColorScheme = lightColorScheme(
    primary = DarkPurple,
    onPrimary = LightPurple,
    primaryContainer = LightPurpleVariant,
    onPrimaryContainer = Black,
    secondary = DarkPurple,
    onSecondary = LightPurple,
    secondaryContainer = LightPurpleVariant,
    onSecondaryContainer = Black,
    background = LightPurple,
    onBackground = Black,
    surface = LightPurple,
    onSurface = Black,
)

@Composable
fun GenerativeAISampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
