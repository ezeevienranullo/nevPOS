package org.verncreation.project.data.model

data class CartItem (
    val id: String,
    val name: String,
    val price: Double,
    var quantity: Int
)