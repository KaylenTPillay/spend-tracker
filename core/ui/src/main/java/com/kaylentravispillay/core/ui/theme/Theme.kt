package com.kaylentravispillay.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle

private val LightColors = lightColorScheme(
    primary = md_light_primary,
    onPrimary = md_light_onPrimary,
    primaryContainer = md_light_primaryContainer,
    onPrimaryContainer = md_light_onPrimaryContainer,
    inversePrimary = md_light_inversePrimary,
    secondary = md_light_secondary,
    onSecondary = md_light_onSecondary,
    secondaryContainer = md_light_secondaryContainer,
    onSecondaryContainer = md_light_onSecondaryContainer,
    tertiary = md_light_tertiary,
    onTertiary = md_light_onTertiary,
    tertiaryContainer = md_light_tertiaryContainer,
    onTertiaryContainer = md_light_onTertiaryContainer,
    background = md_light_background,
    onBackground = md_light_onBackground,
    surface = md_light_surface,
    onSurface = md_light_onSurface,
    surfaceVariant = md_light_surfaceVariant,
    onSurfaceVariant = md_light_onSurfaceVariant,
    surfaceTint = md_light_surfaceTint,
    inverseSurface = md_light_inverseSurface,
    inverseOnSurface = md_light_inverseOnSurface,
    error = md_light_error,
    onError = md_light_onError,
    errorContainer = md_light_errorContainer,
    onErrorContainer = md_light_onErrorContainer,
    outline = md_light_outline,
    outlineVariant = md_light_outlineVariant,
    scrim = md_light_scrim,
    surfaceBright = md_light_surfaceBright,
    surfaceDim = md_light_surfaceDim,
    surfaceContainer = md_light_surfaceContainer,
    surfaceContainerHigh = md_light_surfaceContainerHigh,
    surfaceContainerHighest = md_light_surfaceContainerHighest,
    surfaceContainerLow = md_light_surfaceContainerLow,
    surfaceContainerLowest = md_light_surfaceContainerLowest,
    primaryFixed = md_light_primaryFixed,
    primaryFixedDim = md_light_primaryFixedDim,
    onPrimaryFixed = md_light_onPrimaryFixed,
    onPrimaryFixedVariant = md_light_onPrimaryFixedVariant,
    secondaryFixed = md_light_secondaryFixed,
    secondaryFixedDim = md_light_secondaryFixedDim,
    onSecondaryFixed = md_light_onSecondaryFixed,
    onSecondaryFixedVariant = md_light_onSecondaryFixedVariant,
    tertiaryFixed = md_light_tertiaryFixed,
    tertiaryFixedDim = md_light_tertiaryFixedDim,
    onTertiaryFixed = md_light_onTertiaryFixed,
    onTertiaryFixedVariant = md_light_onTertiaryFixedVariant,
)

private val DarkColors = darkColorScheme(
    primary = md_dark_primary,
    onPrimary = md_dark_onPrimary,
    primaryContainer = md_dark_primaryContainer,
    onPrimaryContainer = md_dark_onPrimaryContainer,
    inversePrimary = md_dark_inversePrimary,
    secondary = md_dark_secondary,
    onSecondary = md_dark_onSecondary,
    secondaryContainer = md_dark_secondaryContainer,
    onSecondaryContainer = md_dark_onSecondaryContainer,
    tertiary = md_dark_tertiary,
    onTertiary = md_dark_onTertiary,
    tertiaryContainer = md_dark_tertiaryContainer,
    onTertiaryContainer = md_dark_onTertiaryContainer,
    background = md_dark_background,
    onBackground = md_dark_onBackground,
    surface = md_dark_surface,
    onSurface = md_dark_onSurface,
    surfaceVariant = md_dark_surfaceVariant,
    onSurfaceVariant = md_dark_onSurfaceVariant,
    surfaceTint = md_dark_surfaceTint,
    inverseSurface = md_dark_inverseSurface,
    inverseOnSurface = md_dark_inverseOnSurface,
    error = md_dark_error,
    onError = md_dark_onError,
    errorContainer = md_dark_errorContainer,
    onErrorContainer = md_dark_onErrorContainer,
    outline = md_dark_outline,
    outlineVariant = md_dark_outlineVariant,
    scrim = md_dark_scrim,
    surfaceBright = md_dark_surfaceBright,
    surfaceDim = md_dark_surfaceDim,
    surfaceContainer = md_dark_surfaceContainer,
    surfaceContainerHigh = md_dark_surfaceContainerHigh,
    surfaceContainerHighest = md_dark_surfaceContainerHighest,
    surfaceContainerLow = md_dark_surfaceContainerLow,
    surfaceContainerLowest = md_dark_surfaceContainerLowest,
    primaryFixed = md_dark_primaryFixed,
    primaryFixedDim = md_dark_primaryFixedDim,
    onPrimaryFixed = md_dark_onPrimaryFixed,
    onPrimaryFixedVariant = md_dark_onPrimaryFixedVariant,
    secondaryFixed = md_dark_secondaryFixed,
    secondaryFixedDim = md_dark_secondaryFixedDim,
    onSecondaryFixed = md_dark_onSecondaryFixed,
    onSecondaryFixedVariant = md_dark_onSecondaryFixedVariant,
    tertiaryFixed = md_dark_tertiaryFixed,
    tertiaryFixedDim = md_dark_tertiaryFixedDim,
    onTertiaryFixed = md_dark_onTertiaryFixed,
    onTertiaryFixedVariant = md_dark_onTertiaryFixedVariant,
)

@Composable
fun TrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        darkTheme -> DarkColors
        else -> LightColors
    }

    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = TrackerMaterialTypography,
            shapes = TrackerMaterialShapes,
            content = content
        )
    }
}

@Suppress("Unused")
object TrackerTheme {
    val spacing: Spacing
        @Composable get() = LocalSpacing.current

    object Typography {
        val numericLarge: TextStyle = NumericLarge
    }
}
