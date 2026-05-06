package com.example.mobileshopapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = CampusGreenDark,
    onPrimary = Color(0xFF003B32),
    primaryContainer = Color(0xFF004F43),
    onPrimaryContainer = Color(0xFFBDF5E9),
    secondary = CampusGoldDark,
    onSecondary = Color(0xFF3F2E00),
    tertiary = CampusCoralDark,
    onTertiary = Color(0xFF5F1123),
    background = Color(0xFF0E1412),
    onBackground = Color(0xFFE3EAE6),
    surface = CampusSurfaceDark,
    onSurface = Color(0xFFE3EAE6),
    surfaceVariant = CampusSurfaceVariantDark,
    onSurfaceVariant = Color(0xFFC8D2CD),
    outline = Color(0xFF8B9A94)
)

private val LightColorScheme = lightColorScheme(
    primary = CampusGreen,
    onPrimary = Color.White,
    primaryContainer = CampusGreenContainer,
    onPrimaryContainer = Color(0xFF083D35),
    secondary = CampusGold,
    onSecondary = Color(0xFF322400),
    tertiary = CampusCoral,
    onTertiary = Color.White,
    background = CampusMist,
    onBackground = CampusInk,
    surface = Color.White,
    onSurface = CampusInk,
    surfaceVariant = Color(0xFFE5ECE8),
    onSurfaceVariant = Color(0xFF3F4B47),
    outline = Color(0xFF71817A)
)

@Composable
fun MobileShopAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
