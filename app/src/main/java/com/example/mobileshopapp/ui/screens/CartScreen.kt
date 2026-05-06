package com.example.mobileshopapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mobileshopapp.model.ShopUiState
import com.example.mobileshopapp.ui.components.CartLineItem
import com.example.mobileshopapp.ui.components.CheckoutSuccessDialog
import com.example.mobileshopapp.ui.components.EmptyCartState
import com.example.mobileshopapp.ui.components.OrderSummary

@Composable
fun CartScreen(
    uiState: ShopUiState,
    onIncrease: (Int) -> Unit,
    onDecrease: (Int) -> Unit,
    onRemove: (Int) -> Unit,
    onCheckout: () -> Unit,
    onDismissCheckoutSuccess: () -> Unit,
    onBrowseProducts: () -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .testTag("cart_screen")
    ) {
        if (uiState.cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                EmptyCartState(
                    onBrowseProducts = onBrowseProducts,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        } else {
            val horizontalPadding = if (maxWidth < 600.dp) 16.dp else 32.dp
            if (maxWidth >= 840.dp) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = horizontalPadding, vertical = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    CartItemsList(
                        uiState = uiState,
                        onIncrease = onIncrease,
                        onDecrease = onDecrease,
                        onRemove = onRemove,
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(bottom = 24.dp)
                    )
                    OrderSummary(
                        subtotalCents = uiState.subtotalCents,
                        taxCents = uiState.taxCents,
                        grandTotalCents = uiState.grandTotalCents,
                        onCheckout = onCheckout,
                        modifier = Modifier.width(360.dp)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        start = horizontalPadding,
                        end = horizontalPadding,
                        top = 18.dp,
                        bottom = 28.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    item {
                        CartHeader(cartItemCount = uiState.cartItemCount)
                    }
                    items(
                        items = uiState.cartItems,
                        key = { item -> item.product.id }
                    ) { item ->
                        CartLineItem(
                            cartItem = item,
                            onIncrease = onIncrease,
                            onDecrease = onDecrease,
                            onRemove = onRemove
                        )
                    }
                    item {
                        OrderSummary(
                            subtotalCents = uiState.subtotalCents,
                            taxCents = uiState.taxCents,
                            grandTotalCents = uiState.grandTotalCents,
                            onCheckout = onCheckout
                        )
                    }
                }
            }
        }
    }

    if (uiState.showCheckoutSuccess) {
        CheckoutSuccessDialog(onDismiss = onDismissCheckoutSuccess)
    }
}

@Composable
private fun CartItemsList(
    uiState: ShopUiState,
    onIncrease: (Int) -> Unit,
    onDecrease: (Int) -> Unit,
    onRemove: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues()
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            CartHeader(cartItemCount = uiState.cartItemCount)
        }
        items(
            items = uiState.cartItems,
            key = { item -> item.product.id }
        ) { item ->
            CartLineItem(
                cartItem = item,
                onIncrease = onIncrease,
                onDecrease = onDecrease,
                onRemove = onRemove
            )
        }
    }
}

@Composable
private fun CartHeader(cartItemCount: Int) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = "Shopping Bag",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "$cartItemCount ${if (cartItemCount == 1) "item" else "items"} selected",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
