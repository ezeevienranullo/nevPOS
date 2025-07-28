package org.verncreation.project.data.repository

import org.verncreation.project.data.model.Product
import org.verncreation.project.data.remote.ProductApi

class ProductRepositoryImpl(private val api: ProductApi) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return api.getProducts()
    }
}