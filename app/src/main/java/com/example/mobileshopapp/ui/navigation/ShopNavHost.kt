package com.example.mobileshopapp.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobileshopapp.ui.components.CampusTopBar
import com.example.mobileshopapp.ui.screens.CartScreen
import com.example.mobileshopapp.ui.screens.HomeScreen
import com.example.mobileshopapp.ui.screens.ProductDetailScreen
import com.example.mobileshopapp.viewmodel.ShopViewModel

@Composable
fun ShopNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: ShopViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentProductId = navBackStackEntry?.arguments?.getInt(ShopRoute.PRODUCT_ID)
    val currentProduct = uiState.products.firstOrNull { it.id == currentProductId }

    Scaffold(
        modifier = modifier,
        topBar = {
            CampusTopBar(
                title = when (currentRoute) {
                    ShopRoute.CART -> "Shopping Bag"
                    ShopRoute.PRODUCT_DETAIL -> currentProduct?.name ?: "Product"
                    else -> "Campus Shop"
                },
                canNavigateBack = currentRoute != null && currentRoute != ShopRoute.HOME,
                cartItemCount = uiState.cartItemCount,
                onNavigateBack = { navController.popBackStack() },
                onCartClick = { navController.navigateSingleTopTo(ShopRoute.CART) }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ShopRoute.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ShopRoute.HOME) {
                HomeScreen(
                    uiState = uiState,
                    onCategorySelected = viewModel::selectCategory,
                    onProductClick = { productId ->
                        navController.navigateSingleTopTo(ShopRoute.productDetail(productId))
                    },
                    onAddToCart = viewModel::addToCart
                )
            }
            composable(
                route = ShopRoute.PRODUCT_DETAIL,
                arguments = listOf(navArgument(ShopRoute.PRODUCT_ID) { type = NavType.IntType })
            ) { entry ->
                val productId = entry.arguments?.getInt(ShopRoute.PRODUCT_ID)
                ProductDetailScreen(
                    product = uiState.products.firstOrNull { product -> product.id == productId },
                    onAddToCart = viewModel::addToCart,
                    onBackToProducts = { navController.navigateHome() }
                )
            }
            composable(route = ShopRoute.CART) {
                CartScreen(
                    uiState = uiState,
                    onIncrease = viewModel::increaseQuantity,
                    onDecrease = viewModel::decreaseQuantity,
                    onRemove = viewModel::removeFromCart,
                    onCheckout = viewModel::checkout,
                    onDismissCheckoutSuccess = viewModel::dismissCheckoutSuccess,
                    onBrowseProducts = { navController.navigateHome() }
                )
            }
        }
    }
}

private fun NavHostController.navigateSingleTopTo(route: String) {
    navigate(route) {
        launchSingleTop = true
    }
}

private fun NavHostController.navigateHome() {
    navigate(ShopRoute.HOME) {
        popUpTo(ShopRoute.HOME) {
            inclusive = false
        }
        launchSingleTop = true
    }
}
