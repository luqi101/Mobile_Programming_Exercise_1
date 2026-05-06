package com.example.mobileshopapp.model

data class CartItem(
    val product: Product,
    val quantity: Int
) {
    val lineTotalCents: Int
        get() = product.priceCents * quantity
}
