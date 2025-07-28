package org.verncreation.project.data.remote

import org.verncreation.project.data.model.Product
import org.verncreation.project.data.model.ProductResponse

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class ProductApi {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun getProducts(): List<Product> {
        val response: HttpResponse = client.get("https://dummyjson.com/products")
        return response.body<ProductResponse>().products
    }
}
