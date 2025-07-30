package org.verncreation.project.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.verncreation.project.data.model.Sale
import org.verncreation.project.presentation.component.BottomNavigationBar
import org.verncreation.project.presentation.navigation.Screen
import org.verncreation.project.presentation.ui.home.HomeScreen
import org.verncreation.project.presentation.ui.products.ProductsScreen
import org.verncreation.project.presentation.ui.sale.SaleScreen
import org.verncreation.project.presentation.ui.settings.SettingsScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.composable
import org.verncreation.project.data.repository.LoginUseCaseImpl
import org.verncreation.project.presentation.ui.innerScreen.newSale.NewSaleScreenWrapper
import org.verncreation.project.presentation.ui.login.LoginScreenWrapper
import org.verncreation.project.presentation.ui.login.LoginViewModel


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
    val currentRoute = currentRoute(navController)

    val bottomNavRoutes = listOf(
        Screen.Home.route,
        Screen.Sale.route,
        Screen.NewSale.route,
        Screen.Products.route,
        Screen.Settings.route
    )
    MaterialTheme {
        Scaffold(
            bottomBar = {
                if (currentRoute in bottomNavRoutes) {
                    BottomNavigationBar(
                        currentRoute = currentRoute,
                        onItemSelected = { route -> navController.navigate(route) }
                    )
                }
            }
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Login.route,
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
                composable(Screen.Login.route) {
                    LoginScreenWrapper(
                        onLoginSuccess = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo("login") { inclusive = true } // prevent going back to login
                            }
                        }
                    )
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