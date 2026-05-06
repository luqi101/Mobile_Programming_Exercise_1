package com.example.mobileshopapp.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

fun formatCents(priceCents: Int): String {
    val dollars = priceCents / 100
    val cents = kotlin.math.abs(priceCents % 100).toString().padStart(2, '0')
    return "$$dollars.$cents"
}

@Composable
fun PriceText(
    priceCents: Int,
    style: TextStyle = MaterialTheme.typography.titleMedium,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Text(
        text = formatCents(priceCents),
        style = style,
        color = color
    )
}
