package org.verncreation.project.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import nevpos.composeapp.generated.resources.Res
import nevpos.composeapp.generated.resources.compose_multiplatform
import org.verncreation.project.Greeting
import org.verncreation.project.data.model.Sale
import org.verncreation.project.presentation.component.BottomNavigationBar
import org.verncreation.project.presentation.navigation.Screen
import org.verncreation.project.presentation.ui.home.HomeScreen
import org.verncreation.project.presentation.ui.products.ProductsScreen
import org.verncreation.project.presentation.ui.sale.SaleScreen
import org.verncreation.project.presentation.ui.settings.SettingsScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.composable
import org.verncreation.project.presentation.ui.innerScreen.newSale.NewSaleScreenWrapper


@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    val dummyHeldSales = listOf(
        Sale("1002", "123.00"),
        Sale("1001", "650.50")
    )
    val dummyRecent = listOf(
        Sale("0999", "370.00"),
        Sale("0998", "540.00")
    )

    MaterialTheme {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    currentRoute = currentRoute(navController),
                    onItemSelected = { route -> navController.navigate(route) }
                )
            }
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(Screen.Home.route) {
                    HomeScreen(
                        onNewSaleClick = { navController.navigate(Screen.NewSale.route) }
                    )
                }
                composable(Screen.Sale.route) {
                    SaleScreen(
                        onNewSale = { navController.navigate(Screen.NewSale.route) },
                        heldSales = dummyHeldSales,
                        recentTransactions = dummyRecent,
                        onResumeSale = {},
                        onViewTransaction = {}
                    )
                }
                composable(Screen.NewSale.route) {
                    NewSaleScreenWrapper(navController)
                }
                composable(Screen.Products.route) {
                    ProductsScreen()
                }
                composable(Screen.Settings.route) {
                    SettingsScreen()
                }
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}