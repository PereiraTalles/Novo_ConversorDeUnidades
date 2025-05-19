package com.example.novo_conversor.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Laranja,
    onPrimary = Creme,
    background = Creme,
    onBackground = Preto,
    surface = CinzaEscuro,
    onSurface = Preto,
)

@Composable
fun MeuTema(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography(),
        content = content
    )
}
