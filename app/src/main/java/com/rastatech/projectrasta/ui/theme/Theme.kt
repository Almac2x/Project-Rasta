package com.rastatech.projectrasta.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

val AppColorPalette = lightColors(
    primary = Primary,
    secondary = Secondary,
    background = Background,
    surface = Surface,
    error = Error,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
    onBackground = OnBackgroundBlack,
    onSurface = OnSurface,
    onError = OnError
)

@Composable
fun ProjectRastaTheme(
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = AppColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}