package org.verncreation.project.presentation.ui.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import org.verncreation.project.data.remote.ProductApi
import org.verncreation.project.data.repository.ProductRepositoryImpl

@Composable
fun ProductsScreen(viewModel: ProductsViewModel = remember { ProductsViewModel(ProductRepositoryImpl(ProductApi())) }) {
    if (viewModel.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.products) { product ->
                Column(modifier = Modifier.padding(4.dp)) {
                    KamelImage(
                        resource = lazyPainterResource(product.thumbnail),
                        contentDescription = product.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp),
                        onLoading = { CircularProgressIndicator() },
                        onFailure = { Text("Image failed to load") }
                    )
                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "₱${product.price}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}