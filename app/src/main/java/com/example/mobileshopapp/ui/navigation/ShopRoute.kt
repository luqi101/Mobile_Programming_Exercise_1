package com.example.mobileshopapp.ui.navigation

object ShopRoute {
    const val HOME = "home"
    const val CART = "cart"
    const val PRODUCT_ID = "productId"
    const val PRODUCT_DETAIL = "product/{$PRODUCT_ID}"

    fun productDetail(productId: Int): String = "product/$productId"
}
