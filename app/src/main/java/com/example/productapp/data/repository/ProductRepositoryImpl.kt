package com.example.productapp.data.repository

import com.example.productapp.data.remote.ProductAPI
import com.example.productapp.domain.models.ProductDTO
import com.example.productapp.domain.models.ProductList
import com.example.productapp.domain.repository.ProductRepository
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(val productAPI: ProductAPI): ProductRepository {
    override suspend fun getProductList(): Response<ProductList> {
        return productAPI.getProducts()
    }

    override suspend fun getProductDetail(id: String): Response<ProductDTO> {
        return productAPI.getDetailProduct(id)
    }
}