package com.example.mobileshopapp.model

data class ShopUiState(
    val products: List<Product> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "All",
    val cartItems: List<CartItem> = emptyList(),
    val showCheckoutSuccess: Boolean = false
) {
    val visibleProducts: List<Product>
        get() = if (selectedCategory == "All") {
            products
        } else {
            products.filter { it.category == selectedCategory }
        }

    val subtotalCents: Int
        get() = cartItems.sumOf { it.lineTotalCents }

    val taxCents: Int
        get() = (subtotalCents * TAX_PERCENT + 50) / 100

    val grandTotalCents: Int
        get() = subtotalCents + taxCents

    val cartItemCount: Int
        get() = cartItems.sumOf { it.quantity }

    companion object {
        const val TAX_PERCENT = 13
    }
}
