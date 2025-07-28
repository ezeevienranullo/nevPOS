package org.verncreation.project.data.repository

import org.verncreation.project.data.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}