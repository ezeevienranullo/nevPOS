package org.verncreation.project.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.verncreation.project.data.remote.ProductApi
import org.verncreation.project.data.repository.ProductRepositoryImpl
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

@Composable
fun HomeScreen(viewModel: HomeViewModel = remember { HomeViewModel(ProductRepositoryImpl(ProductApi())) }) {
    if (viewModel.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(Modifier.fillMaxSize()) {
            items(viewModel.products) { product ->
                Column(modifier = Modifier.padding(8.dp)) {
                    KamelImage(
                        resource = lazyPainterResource(product.thumbnail),
                        contentDescription = product.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        onLoading = { CircularProgressIndicator() },
                        onFailure = { Text("Image failed to load") }
                    )
                    Text(text = product.title, style = MaterialTheme.typography.titleMedium)
                    Text(text = "₱${product.price}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}