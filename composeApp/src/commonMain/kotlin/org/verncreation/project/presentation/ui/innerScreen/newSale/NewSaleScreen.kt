package org.verncreation.project.presentation.ui.innerScreen.newSale

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.verncreation.project.data.model.CartItem
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewSaleScreen(
    cartItems: List<CartItem>,
    onIncreaseQty: (CartItem) -> Unit,
    onDecreaseQty: (CartItem) -> Unit,
    onRemoveItem: (CartItem) -> Unit,
    onCheckout: () -> Unit,
    onCancel: () -> Unit,
    onAddProduct: () -> Unit
) {
    val subtotal = cartItems.sumOf { it.price * it.quantity }
    val discount = 0.0 // You can add logic here
    val total = subtotal - discount

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("New Sale") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddProduct) {
                Icon(Icons.Default.AddShoppingCart, contentDescription = "Add Product")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Customer: Walk-in", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cartItems) { item ->
                    CartItemRow(item, onIncreaseQty, onDecreaseQty, onRemoveItem)
                }
            }

            Divider(Modifier.padding(vertical = 8.dp))

            Text("Subtotal: ₱%.2f".format(subtotal))
            Text("Discount: ₱%.2f".format(discount))
            Text("Total: ₱%.2f".format(total), style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(onClick = onCancel, modifier = Modifier.weight(1f)) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = onCheckout, modifier = Modifier.weight(1f)) {
                    Text("Checkout")
                }
            }
        }
    }
}

@Composable
fun CartItemRow(
    item: CartItem,
    onIncreaseQty: (CartItem) -> Unit,
    onDecreaseQty: (CartItem) -> Unit,
    onRemoveItem: (CartItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(item.name, style = MaterialTheme.typography.bodyLarge)
                Text("₱%.2f".format(item.price), style = MaterialTheme.typography.bodySmall)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { onDecreaseQty(item) }) {
                    Icon(Icons.Default.Remove, contentDescription = "Decrease")
                }
                Text("${item.quantity}", style = MaterialTheme.typography.bodyLarge)
                IconButton(onClick = { onIncreaseQty(item) }) {
                    Icon(Icons.Default.Add, contentDescription = "Increase")
                }
                IconButton(onClick = { onRemoveItem(item) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Remove")
                }
            }
        }
    }
}

@Preview()
@Composable
fun NewSalePreview() {
    val sampleCart = listOf(
        CartItem("1", "Coke 330ml", 25.0, 1),
        CartItem("2", "Fried Chicken", 120.0, 2)
    )

    NewSaleScreen(
        cartItems = sampleCart,
        onIncreaseQty = {},
        onDecreaseQty = {},
        onRemoveItem = {},
        onCheckout = {},
        onCancel = {},
        onAddProduct = {}
    )
}