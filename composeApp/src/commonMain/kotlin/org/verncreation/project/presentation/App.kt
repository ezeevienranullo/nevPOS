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
import org.verncreation.project.presentation.component.BottomNavigationBar
import org.verncreation.project.presentation.navigation.Screen
import org.verncreation.project.presentation.ui.home.HomeScreen
import org.verncreation.project.presentation.ui.products.ProductsScreen
import org.verncreation.project.presentation.ui.settings.SettingsScreen

@Composable
@Preview
fun App() {
    var selectedScreen by remember { mutableStateOf(Screen.Home) }

    MaterialTheme {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    selected = selectedScreen,
                    onItemSelected = { selectedScreen = it }
                )
            }
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(it)) {
                when (selectedScreen) {
                    Screen.Home -> HomeScreen()
                    Screen.Products -> ProductsScreen()
                    Screen.Settings -> SettingsScreen()
                }
            }
        }
    }
}
