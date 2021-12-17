package com.rastatech.projectrasta.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
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
fun ProjectRastaTheme( darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit,
) {

    val colors = if (darkTheme) {
        AppColorPalette
    } else {
        AppColorPalette
    }

    MaterialTheme(
        colors = AppColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}