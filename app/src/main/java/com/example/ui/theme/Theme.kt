package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val TacticalColorScheme = darkColorScheme(
    primary = StationCyanPrimary,
    onPrimary = Color(0xFF040E14),
    primaryContainer = StationNavySuit,
    onPrimaryContainer = Color(0xFFE0F7FA),
    secondary = StationCyanSecondary,
    onSecondary = Color(0xFF040E14),
    tertiary = StationAmberWarning,
    onTertiary = Color(0xFF1E1100),
    background = StationDarkBackground,
    onBackground = StationTextLight,
    surface = StationSurface,
    onSurface = StationTextLight,
    surfaceVariant = StationSurfaceVariant,
    onSurfaceVariant = StationTextMuted,
    outline = StationSlateBorder
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = TacticalColorScheme,
        typography = Typography,
        content = content
    )
}

