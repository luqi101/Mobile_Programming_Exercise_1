package com.example.mobileshopapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mobileshopapp.model.ShopUiState
import com.example.mobileshopapp.ui.components.CategoryChips
import com.example.mobileshopapp.ui.components.ProductCard

@Composable
fun HomeScreen(
    uiState: ShopUiState,
    onCategorySelected: (String) -> Unit,
    onProductClick: (Int) -> Unit,
    onAddToCart: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val horizontalPadding = if (maxWidth < 600.dp) 16.dp else 32.dp
        val minCellWidth = if (maxWidth < 420.dp) 158.dp else 190.dp

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = minCellWidth),
            modifier = Modifier
                .fillMaxSize()
                .testTag("home_screen"),
            contentPadding = PaddingValues(
                start = horizontalPadding,
                end = horizontalPadding,
                top = 18.dp,
                bottom = 28.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                HomeHeader(
                    visibleCount = uiState.visibleProducts.size,
                    selectedCategory = uiState.selectedCategory
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                CategoryChips(
                    categories = uiState.categories,
                    selectedCategory = uiState.selectedCategory,
                    onCategorySelected = onCategorySelected,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp, bottom = 2.dp)
                )
            }
            items(
                items = uiState.visibleProducts,
                key = { product -> product.id }
            ) { product ->
                ProductCard(
                    product = product,
                    onProductClick = onProductClick,
                    onAddToCart = onAddToCart
                )
            }
        }
    }
}

@Composable
private fun HomeHeader(
    visibleCount: Int,
    selectedCategory: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "Campus Essentials",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "Study gear, drinkware, apparel, and tech for class days.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = if (selectedCategory == "All") {
                "$visibleCount products"
            } else {
                "$visibleCount $selectedCategory products"
            },
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
