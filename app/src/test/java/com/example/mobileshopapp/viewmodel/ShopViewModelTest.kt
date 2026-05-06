package com.example.mobileshopapp.viewmodel

import com.example.mobileshopapp.model.ShopUiState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ShopViewModelTest {
    @Test
    fun addIncreaseDecreaseAndRemoveUpdateCartTotals() {
        val viewModel = ShopViewModel()
        val hoodie = viewModel.uiState.value.products.first { it.id == 1 }
        val notebook = viewModel.uiState.value.products.first { it.id == 2 }

        viewModel.addToCart(hoodie.id)
        viewModel.addToCart(notebook.id)
        viewModel.increaseQuantity(hoodie.id)

        val withItems = viewModel.uiState.value
        val expectedSubtotal = hoodie.priceCents * 2 + notebook.priceCents
        val expectedTax = (expectedSubtotal * ShopUiState.TAX_PERCENT + 50) / 100

        assertEquals(3, withItems.cartItemCount)
        assertEquals(expectedSubtotal, withItems.subtotalCents)
        assertEquals(expectedTax, withItems.taxCents)
        assertEquals(expectedSubtotal + expectedTax, withItems.grandTotalCents)

        viewModel.decreaseQuantity(hoodie.id)
        assertEquals(2, viewModel.uiState.value.cartItemCount)

        viewModel.removeFromCart(notebook.id)
        assertEquals(1, viewModel.uiState.value.cartItemCount)
        assertEquals(hoodie.priceCents, viewModel.uiState.value.subtotalCents)
    }

    @Test
    fun checkoutClearsBagAndShowsSuccessDialogFlag() {
        val viewModel = ShopViewModel()

        viewModel.addToCart(1)
        viewModel.checkout()

        assertTrue(viewModel.uiState.value.showCheckoutSuccess)
        assertEquals(0, viewModel.uiState.value.cartItemCount)
        assertEquals(0, viewModel.uiState.value.grandTotalCents)

        viewModel.dismissCheckoutSuccess()
        assertFalse(viewModel.uiState.value.showCheckoutSuccess)
    }

    @Test
    fun categorySelectionFiltersVisibleProducts() {
        val viewModel = ShopViewModel()

        viewModel.selectCategory("Study")

        assertTrue(viewModel.uiState.value.visibleProducts.isNotEmpty())
        assertTrue(viewModel.uiState.value.visibleProducts.all { it.category == "Study" })
    }
}
