package org.verncreation.project.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String
)

@Serializable
data class ProductResponse(
    val products: List<Product>
)
