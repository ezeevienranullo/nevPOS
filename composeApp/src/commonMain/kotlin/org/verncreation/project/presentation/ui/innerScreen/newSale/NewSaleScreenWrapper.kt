package org.verncreation.project.presentation.ui.innerScreen.newSale

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import org.verncreation.project.data.model.CartItem
import org.verncreation.project.presentation.navigation.Screen
import androidx.navigation.NavController
import androidx.navigation.NavHostController



@Composable
fun NewSaleScreenWrapper(navController: NavController) {
    val cartItems = remember { mutableStateListOf<CartItem>() }

    NewSaleScreen(
        cartItems = cartItems,
        onIncreaseQty = { it.quantity++ },
        onDecreaseQty = {
            if (it.quantity > 1) it.quantity-- else cartItems.remove(it)
        },
        onRemoveItem = { cartItems.remove(it) },
        onAddProduct = {
            cartItems.add(CartItem("3", "New Product", 100.0, 1))
        },
        onCheckout = {
            navController.popBackStack(Screen.Home.route, false)
            // or navigate to checkout screen
        },
        onCancel = {
            navController.popBackStack()
        }
    )
}
