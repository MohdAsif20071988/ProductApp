package com.example.productapp.domain.repository

import com.example.productapp.domain.models.ProductDTO
import com.example.productapp.domain.models.ProductList
import retrofit2.Response

interface ProductRepository {
    suspend fun getProductList(): Response<ProductList>
    suspend fun getProductDetail(id: String): Response<ProductDTO>
}