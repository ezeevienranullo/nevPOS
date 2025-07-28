package org.verncreation.project.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.verncreation.project.presentation.navigation.Screen

@Composable
fun BottomNavigationBar(selected: Screen, onItemSelected: (Screen) -> Unit) {
    NavigationBar {
        Screen.values().forEach { screen ->
            NavigationBarItem(
                selected = screen == selected,
                onClick = { onItemSelected(screen) },
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) }
            )
        }
    }
}
