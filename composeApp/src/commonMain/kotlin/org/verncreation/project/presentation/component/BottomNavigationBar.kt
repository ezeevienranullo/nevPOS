package org.verncreation.project.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.verncreation.project.presentation.navigation.Screen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Settings
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.text.capitalize

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onItemSelected: (String) -> Unit
) {
    val items = listOf(Screen.Home, Screen.Sale, Screen.Products, Screen.Settings)
    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        when (screen) {
                            Screen.Home -> Icons.Default.Home
                            Screen.Sale -> Icons.Default.PointOfSale
                            Screen.Products -> Icons.Default.Inventory
                            Screen.Settings -> Icons.Default.Settings
                            else -> Icons.Default.Home
                        },
                        contentDescription = screen.route
                    )
                },
                label = { Text(screen.route.replace("_", " ").capitalize()) },
                selected = currentRoute == screen.route,
                onClick = { onItemSelected(screen.route) }
            )
        }
    }
}
