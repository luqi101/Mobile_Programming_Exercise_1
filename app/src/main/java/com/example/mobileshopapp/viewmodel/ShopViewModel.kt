package com.example.mobileshopapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mobileshopapp.data.ProductCatalog
import com.example.mobileshopapp.model.CartItem
import com.example.mobileshopapp.model.ShopUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShopViewModel : ViewModel() {
    private val cartQuantities = mutableMapOf<Int, Int>()

    private val _uiState = MutableStateFlow(createState())
    val uiState: StateFlow<ShopUiState> = _uiState.asStateFlow()

    fun selectCategory(category: String) {
        if (category !in ProductCatalog.categories) return
        publish(selectedCategory = category)
    }

    fun addToCart(productId: Int) {
        if (!hasProduct(productId)) return
        cartQuantities[productId] = (cartQuantities[productId] ?: 0) + 1
        publish()
    }

    fun increaseQuantity(productId: Int) {
        if (productId !in cartQuantities) return
        cartQuantities[productId] = cartQuantities.getValue(productId) + 1
        publish()
    }

    fun decreaseQuantity(productId: Int) {
        val currentQuantity = cartQuantities[productId] ?: return
        if (currentQuantity <= 1) {
            cartQuantities.remove(productId)
        } else {
            cartQuantities[productId] = currentQuantity - 1
        }
        publish()
    }

    fun removeFromCart(productId: Int) {
        if (cartQuantities.remove(productId) != null) {
            publish()
        }
    }

    fun checkout() {
        if (cartQuantities.isEmpty()) return
        cartQuantities.clear()
        publish(showCheckoutSuccess = true)
    }

    fun dismissCheckoutSuccess() {
        publish(showCheckoutSuccess = false)
    }

    private fun publish(
        selectedCategory: String = _uiState.value.selectedCategory,
        showCheckoutSuccess: Boolean = _uiState.value.showCheckoutSuccess
    ) {
        _uiState.value = createState(selectedCategory, showCheckoutSuccess)
    }

    private fun createState(
        selectedCategory: String = ProductCatalog.ALL_CATEGORY,
        showCheckoutSuccess: Boolean = false
    ): ShopUiState {
        val cartItems = ProductCatalog.products.mapNotNull { product ->
            val quantity = cartQuantities[product.id] ?: return@mapNotNull null
            CartItem(product = product, quantity = quantity)
        }

        return ShopUiState(
            products = ProductCatalog.products,
            categories = ProductCatalog.categories,
            selectedCategory = selectedCategory,
            cartItems = cartItems,
            showCheckoutSuccess = showCheckoutSuccess
        )
    }

    private fun hasProduct(productId: Int): Boolean {
        return ProductCatalog.products.any { it.id == productId }
    }
}
