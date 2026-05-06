package com.example.mobileshopapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobileshopapp.model.Product

@Composable
fun ProductImage(
    product: Product,
    modifier: Modifier = Modifier
) {
    val accent = Color(product.accentColor)
    val backgroundAlpha = if (isSystemInDarkTheme()) 0.28f else 0.14f

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(accent.copy(alpha = backgroundAlpha)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = product.imageResId),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            contentScale = ContentScale.Fit
        )
    }
}
