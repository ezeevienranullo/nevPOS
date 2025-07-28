package org.verncreation.project.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.PointOfSale

//enum class Screen(val title: String, val icon: ImageVector) {
//    Home("Home", Icons.Default.Home),
//    Sale("Sale", Icons.Default.PointOfSale),
//    Products("Products", Icons.Default.ShoppingCart),
//    Settings("Settings", Icons.Default.Settings)
//}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Sale : Screen("sale")
    object NewSale : Screen("new_sale")
    object Products : Screen("products")
    object Settings : Screen("settings")
}