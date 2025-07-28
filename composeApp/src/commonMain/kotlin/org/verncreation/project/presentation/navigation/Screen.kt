package org.verncreation.project.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart

enum class Screen(val title: String, val icon: ImageVector) {
    Home("Home", Icons.Default.Home),
    Products("Products", Icons.Default.ShoppingCart),
    Settings("Settings", Icons.Default.Settings)
}
