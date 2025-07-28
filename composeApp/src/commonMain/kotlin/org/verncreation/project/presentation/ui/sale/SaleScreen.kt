package org.verncreation.project.presentation.ui.sale

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.verncreation.project.data.model.Sale
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaleScreen(
    onNewSale: () -> Unit,
    heldSales: List<Sale>,
    recentTransactions: List<Sale>,
    onResumeSale: (Sale) -> Unit,
    onViewTransaction: (Sale) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Sale") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNewSale) {
                Icon(Icons.Default.Add, contentDescription = "New Sale")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Held Sales", style = MaterialTheme.typography.titleMedium)
            if (heldSales.isEmpty()) {
                Text("No held sales.", style = MaterialTheme.typography.bodySmall)
            } else {
                heldSales.forEach { sale ->
                    SaleItem(
                        sale = sale,
                        onClick = { onResumeSale(sale) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Recent Transactions", style = MaterialTheme.typography.titleMedium)
            recentTransactions.forEach { sale ->
                SaleItem(
                    sale = sale,
                    onClick = { onViewTransaction(sale) }
                )
            }
        }
    }
}

@Composable
fun SaleItem(sale: Sale, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Sale #${sale.id}")
            Text("₱${sale.total}")
        }
    }
}

@Preview
@Composable
fun SaleScreenPreview() {
    val dummyHeldSales = listOf(
        Sale("1002", "123.00"),
        Sale("1001", "650.50")
    )
    val dummyRecent = listOf(
        Sale("0999", "370.00"),
        Sale("0998", "540.00")
    )

    SaleScreen(
        onNewSale = {},
        heldSales = dummyHeldSales,
        recentTransactions = dummyRecent,
        onResumeSale = {},
        onViewTransaction = {}
    )
}