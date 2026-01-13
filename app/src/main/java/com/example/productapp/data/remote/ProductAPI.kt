package com.example.productapp.data.remote

import com.example.productapp.common.PRODUCT_DETAIL_API
import com.example.productapp.common.PRODUCT_LIST_API
import com.example.productapp.domain.models.ProductDTO
import com.example.productapp.domain.models.ProductList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPI {
    @GET(PRODUCT_LIST_API)
    suspend fun getProducts() : Response<ProductList>

    @GET(PRODUCT_DETAIL_API)
    suspend fun getDetailProduct(@Path("id") id: String) : Response<ProductDTO>
}