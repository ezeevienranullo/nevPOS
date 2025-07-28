package org.verncreation.project.presentation.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.verncreation.project.data.model.Product
import org.verncreation.project.data.repository.ProductRepository

class HomeViewModel(private val repository: ProductRepository) {
    var products by mutableStateOf<List<Product>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    init {
        loadProducts()
    }

    private fun loadProducts() {
        CoroutineScope(Dispatchers.Default).launch {
            isLoading = true
            products = repository.getProducts()
            isLoading = false
        }
    }
}