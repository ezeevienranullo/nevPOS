package org.verncreation.project.presentation.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import org.verncreation.project.presentation.navigation.Screen
import androidx.navigation.NavHostController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNewSaleClick: () -> Unit) {
    val dashboardItems = listOf(
        HomeItem("New Sale", Icons.Default.PointOfSale) {
            onNewSaleClick()
        /* Navigate */
//            navController.navigate(Screen.NewSale.route)
        },
        HomeItem("Products", Icons.Default.Inventory) { /* Navigate */ },
        HomeItem("Customers", Icons.Default.Person) { /* Navigate */ },
        HomeItem("Transactions", Icons.Default.Receipt) { /* Navigate */ },
        HomeItem("Reports", Icons.Default.BarChart) { /* Navigate */ },
        HomeItem("Settings", Icons.Default.Settings) { /* Navigate */ },
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("POS Dashboard") })
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(dashboardItems) { item ->
                HomeCard(item)
            }
        }
    }
}

data class HomeItem(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)



@Composable
fun HomeCard(item: HomeItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // Makes square cards
            .clickable { item.onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}