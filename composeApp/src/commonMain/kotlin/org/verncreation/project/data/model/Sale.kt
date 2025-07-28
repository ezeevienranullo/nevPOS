package org.verncreation.project.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Sale(
    val id: String,
    val total: String
)

@Serializable
data class SaleResponse(
    val sales: List<Sale>
)
