package com.example.mobileshopapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mobileshopapp.model.Product
import com.example.mobileshopapp.ui.components.PriceText
import com.example.mobileshopapp.ui.components.ProductImage

@Composable
fun ProductDetailScreen(
    product: Product?,
    onAddToCart: (Int) -> Unit,
    onBackToProducts: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (product == null) {
        MissingProductScreen(
            onBackToProducts = onBackToProducts,
            modifier = modifier
        )
        return
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .testTag("product_detail_${product.id}")
    ) {
        val isWide = maxWidth >= 760.dp
        val horizontalPadding = if (maxWidth < 600.dp) 20.dp else 40.dp

        if (isWide) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = horizontalPadding, vertical = 28.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProductImage(
                    product = product,
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                )
                ProductDetailContent(
                    product = product,
                    onAddToCart = onAddToCart,
                    onBackToProducts = onBackToProducts,
                    modifier = Modifier
                        .weight(1f)
                        .widthIn(max = 520.dp)
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = horizontalPadding, vertical = 18.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                ProductImage(
                    product = product,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.05f)
                )
                ProductDetailContent(
                    product = product,
                    onAddToCart = onAddToCart,
                    onBackToProducts = onBackToProducts
                )
            }
        }
    }
}

@Composable
private fun ProductDetailContent(
    product: Product,
    onAddToCart: (Int) -> Unit,
    onBackToProducts: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = product.category,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = product.name,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        PriceText(
            priceCents = product.priceCents,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = product.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Button(
            onClick = { onAddToCart(product.id) },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("detail_add_to_bag")
        ) {
            Text("Add to Bag")
        }
        OutlinedButton(
            onClick = onBackToProducts,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Products")
        }
    }
}

@Composable
private fun MissingProductScreen(
    onBackToProducts: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "Product not found",
                style = MaterialTheme.typography.headlineMedium
            )
            OutlinedButton(onClick = onBackToProducts) {
                Text("Back to Products")
            }
        }
    }
}
