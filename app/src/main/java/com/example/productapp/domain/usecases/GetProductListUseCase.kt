package com.example.productapp.domain.usecases

import com.example.productapp.domain.models.ProductAPIResponse
import com.example.productapp.domain.repository.ProductRepository
import com.example.productapp.presentation.models.Product
import com.example.productapp.utility.toProduct
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend operator fun invoke() = flow<ProductAPIResponse<List<Product>>> {
        val response = productRepository.getProductList()

        if (response.isSuccessful) {
            response.body()?.let { productList ->
                emit(ProductAPIResponse.SUCCESS(productList.products.map { it.toProduct() }))
                return@flow
            }
            emit(ProductAPIResponse.ERROR(Exception("Something went wrong. Please try again.")))
        } else {
            emit(ProductAPIResponse.ERROR(Exception("Something went wrong. Please try again.")))
        }
    }
}